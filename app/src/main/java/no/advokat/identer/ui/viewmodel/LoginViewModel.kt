package no.advokat.identer.ui.viewmodel


import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.openid.appauth.*
import no.advokat.identer.data.model.UserInfoModel
import no.advokat.identer.data.repository.AuthRepository
import no.advokat.identer.ui.screen.login.LoginUiState
import no.advokat.identer.utils.Event
import no.advokat.identer.utils.SecureStorage
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject
import androidx.core.net.toUri

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val authRepository: AuthRepository,
    private val secureStorage: SecureStorage
) : ViewModel() {

    private val authService = AuthorizationService(application.applicationContext)

    // UI State
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Event to launch the authorization intent
    private val _authIntent = MutableSharedFlow<Event<Intent>>()
    val authIntent: SharedFlow<Event<Intent>> = _authIntent.asSharedFlow()

    // Authorization Service Configuration
    private var authServiceConfig: AuthorizationServiceConfiguration? = null


    init {
        fetchAuthServiceConfig()

        if (ContextCompat.checkSelfPermission(application, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            Log.i("LoginViewModel", "Failed to fetch configuration")

            // ActivityCompat.requestPermissions( this, arrayOf( android.Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }
    }

    private fun fetchAuthServiceConfig() {
        AuthorizationServiceConfiguration.fetchFromUrl(
            "https://apitest.vipps.no/access-management-1.0/access/.well-known/openid-configuration".toUri()
        ) { config, ex ->
            if (config != null) {
                authServiceConfig = config
            } else {
                Log.e("LoginViewModel", "Failed to fetch configuration", ex)
                _uiState.value = LoginUiState.Error("Failed to fetch configuration")
            }
        }
    }

    fun startLogin() {
        val config = authServiceConfig ?: run {
            _uiState.value = LoginUiState.Error("Authorization configuration not available")
            return
        }

        val authRequest = AuthorizationRequest.Builder(
            config,
            "",
            ResponseTypeValues.CODE,
            "http://no.advokat.forsvarer".toUri()
        )
            .setScope("openid name phoneNumber address")
            .build()

        val authIntent = authService.getAuthorizationRequestIntent(authRequest)
        viewModelScope.launch {
            _authIntent.emit(Event(authIntent))
        }
    }

    fun handleAuthResponse(resultIntent: Intent) {
        val response = AuthorizationResponse.fromIntent(resultIntent)
        val exception = AuthorizationException.fromIntent(resultIntent)

        if (response != null) {
            exchangeToken(response)
        } else if (exception != null) {
            Log.e("LoginViewModel", "Authorization Failed: ${exception.toJsonString()}")
            _uiState.value = LoginUiState.Error("Authorization Failed: ${exception.errorDescription}")
        }
    }

    private fun exchangeToken(response: AuthorizationResponse) {
        val clientSecret = ""
        val clientAuthentication = ClientSecretBasic(clientSecret)

        authService.performTokenRequest(
            response.createTokenExchangeRequest(),
            clientAuthentication
        ) { tokenResponse, ex ->
            if (tokenResponse != null) {
                Log.d("LoginViewModel", "Token Exchange Successful: ${tokenResponse.accessToken}")
                val accessToken = tokenResponse.accessToken.orEmpty()
                fetchUserInfo(accessToken)
            } else {
                Log.e("LoginViewModel", "Token Exchange Failed", ex)
                _uiState.value = LoginUiState.Error("Token Exchange Failed: ${ex?.errorDescription}")
            }
        }
    }

    private fun fetchUserInfo(accessToken: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://apitest.vipps.no/vipps-userinfo-api/userinfo")
                .addHeader("Authorization", "Bearer $accessToken")
                .build()

            try {
                val response = withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }
                response.use {
                    if (response.isSuccessful) {
                        val responseBodyString = response.body?.string()
                        if (responseBodyString != null) {
                            val gson = Gson()
                            val userInfo = gson.fromJson(responseBodyString, UserInfoModel::class.java)
                            // Save the access token securely
                            secureStorage.saveAccessToken(accessToken)
                            // Update AuthRepository
                            authRepository.setUserInfo(userInfo)
                            authRepository.setAuthenticated(true)
                            // Update UI state
                            _uiState.value = LoginUiState.Success
                        } else {
                            _uiState.value = LoginUiState.Error("Response body is null")
                        }
                    } else {
                        _uiState.value = LoginUiState.Error("Failed to fetch user info: ${response.message}")
                    }
                }
            } catch (e: IOException) {
                _uiState.value = LoginUiState.Error("Failed to fetch user info: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        authService.dispose()
    }
}