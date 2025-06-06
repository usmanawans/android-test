package no.advokat.identer.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import no.advokat.identer.data.model.UserInfoModel
import no.advokat.identer.data.repository.AuthRepository


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val isUserAuthenticated: StateFlow<Boolean> = authRepository.isUserAuthenticated
    val userInfo: StateFlow<UserInfoModel?> = authRepository.userInfo
    suspend fun logout() {
        authRepository.logout()
    }
}