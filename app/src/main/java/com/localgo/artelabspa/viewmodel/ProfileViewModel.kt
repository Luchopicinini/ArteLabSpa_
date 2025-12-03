package com.localgo.artelabspa.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelabspa.data.local.SessionManager
import com.localgo.artelabspa.data.repository.AvatarRepositoryBackend
import com.localgo.artelabspa.model.ProfileUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    application: Application,
    private val avatarRepository: AvatarRepositoryBackend,
    private val sessionManager: SessionManager,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch(dispatcher) {

            val email = sessionManager.getEmail()
            val avatarUrl = sessionManager.getAvatar()

            _uiState.update { current ->
                current.copy(
                    userEmail = email ?: "Sin correo",
                    userName = email?.substringBefore("@")
                        ?.replaceFirstChar { it.uppercase() }
                        ?: "Usuario",
                    avatarUrl = avatarUrl,
                    isLoading = false
                )
            }
        }
    }

    fun uploadAvatar(uri: Uri) {
        viewModelScope.launch(dispatcher) {

            Log.d("PROFILE", "Subiendo avatar desde URI: $uri")

            _uiState.update { it.copy(isUploading = true) }

            val newUrl = avatarRepository.uploadAvatar(uri)

            if (newUrl != null) {
                Log.d("PROFILE", "Avatar actualizado: $newUrl")

                sessionManager.saveAvatar(newUrl)

                _uiState.update {
                    it.copy(
                        avatarUrl = newUrl,
                        isUploading = false,
                        error = null
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isUploading = false,
                        error = "Error subiendo imagen"
                    )
                }
            }
        }
    }
}
