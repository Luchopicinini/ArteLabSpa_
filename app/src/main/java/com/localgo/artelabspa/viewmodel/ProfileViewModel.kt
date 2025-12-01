
package com.localgo.artelabspa.viewmodel

import android.app.Application//importa la clase Application de Android para crear una subclase global
import android.net.Uri //importa un identificador de recursos que es para señalar datos
import androidx.lifecycle.AndroidViewModel //para que no tenga errores ni fugas de datos
import androidx.lifecycle.viewModelScope //viewmodel lanza corrutinas
import com.localgo.artelabspa.data.local.UserSessionManager
import com.localgo.artelabspa.model.ProfileUiState
import com.localgo.artelabspa.data.repository.AvatarRepository
//avisar cuando la ui cambio algo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
//launch en las corrutinas
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {//viewmodel de perfil y su contexto

    private val avatarRepository = AvatarRepository(application.applicationContext)//repositorio de avatar
    private val sessionManager = UserSessionManager(application.applicationContext) //administrador de la sesion de usuario

    private val _uiState = MutableStateFlow(ProfileUiState()) //la ui se actualiza sola cuando _uiState cambia
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()//la ui solo puede mirar, no puede cambiar el estado

    init {//escucha (observa cambios)
        observeSavedAvatar()
        observeUserEmail()
    }

    //  Escucha el correo guardado en DataStore
    private fun observeUserEmail() {
        viewModelScope.launch {
            sessionManager.getUserEmail().collectLatest { email ->
                _uiState.update {
                    it.copy(
                        userEmail = email ?: "Sin correo guardado",//si no hay correo es sin correo muestra "Sin correo guardado”.
                        userName = email?.substringBefore("@")?.replaceFirstChar { c -> c.uppercase() } ?: "Usuario" //va antes del @ del correo y pone letra en mayuscula. si no hay correo mustra usuario
                    )
                }
            }
        }
    }

    // Escucha cambios en el avatar
    private fun observeSavedAvatar() {
        viewModelScope.launch {
            avatarRepository.getAvatarUri().collectLatest { savedUri ->
                _uiState.update { it.copy(avatarUri = savedUri, isLoading = false) }//pone avatar uri y pone false en isLoading porque ya cargo
            }
        }
    }

    fun updateAvatar(uri: Uri?) {
        viewModelScope.launch {
            avatarRepository.saveAvatarUri(uri)//guarda nuevo avatar
        }
    }
}
