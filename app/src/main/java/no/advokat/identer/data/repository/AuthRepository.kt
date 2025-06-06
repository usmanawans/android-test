package no.advokat.identer.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import no.advokat.identer.data.model.UserInfoModel
import no.advokat.identer.utils.SecureStorage
import javax.inject.Inject
import javax.inject.Singleton

// AuthRepository.kt

@Singleton
class AuthRepository @Inject constructor(
    private val secureStorage: SecureStorage
) {

    private val _isUserAuthenticated = MutableStateFlow(false)
    val isUserAuthenticated: StateFlow<Boolean> = _isUserAuthenticated

    private val _userInfo = MutableStateFlow<UserInfoModel?>(null)
    val userInfo: StateFlow<UserInfoModel?> = _userInfo

    fun setAuthenticated(isAuthenticated: Boolean) {
        _isUserAuthenticated.value = isAuthenticated
    }

    fun setUserInfo(userInfo: UserInfoModel) {
        _userInfo.value = userInfo
    }

    fun getUserInfo(): UserInfoModel? {
        return _userInfo.value
    }

    suspend fun logout() {
        secureStorage.clearAccessToken()
        _isUserAuthenticated.value = false
    }
}