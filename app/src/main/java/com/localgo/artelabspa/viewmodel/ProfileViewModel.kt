package com.localgo.artelabspa.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelabspa.data.local.UserSessionManager
import com.localgo.artelabspa.data.repository.AvatarRepository
import com.localgo.artelabspa.model.ProfileUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    application: Application,
    private val avatarRepository: AvatarRepository,
    private val sessionManager: UserSessionManager,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main // para tests
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        observeSavedAvatar()
        observeUserEmail()
    }

    private fun observeUserEmail() {
        viewModelScope.launch(dispatcher) {
            sessionManager.getUserEmail().collectLatest { email ->
                _uiState.update {
                    it.copy(
                        userEmail = email ?: "Sin correo guardado",
                        userName = email?.substringBefore("@")
                            ?.replaceFirstChar { c -> c.uppercase() } ?: "Usuario"
                    )
                }
            }
        }
    }

    private fun observeSavedAvatar() {
        viewModelScope.launch(dispatcher) {
            avatarRepository.getAvatarUri().collectLatest { savedUri ->
                _uiState.update {
                    it.copy(avatarUri = savedUri, isLoading = false)
                }
            }
        }
    }

    fun updateAvatar(uri: Uri?) {
        viewModelScope.launch(dispatcher) {
            avatarRepository.saveAvatarUri(uri)
        }
    }
}
