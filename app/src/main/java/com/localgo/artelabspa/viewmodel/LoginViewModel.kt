package com.localgo.artelabspa.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelabspa.data.local.UserSessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    // Constructor principal: usa UserSessionManager real
    private var sessionManager: UserSessionManager = UserSessionManager(application.applicationContext)

    // Constructor secundario: inyecta un UserSessionManager fake o mock (para pruebas unitarias)
    constructor(sessionManager: UserSessionManager, app: Application) : this(app) {
        this.sessionManager = sessionManager
    }

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun onEmailChanged(value: String) {
        _email.value = value
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
    }

    fun login(onSuccess: () -> Unit) {
        val emailValue = _email.value.trim()
        val passwordValue = _password.value

        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|cl)$")

        when {
            emailValue.isEmpty() || passwordValue.isEmpty() ->
                _errorMessage.value = "Completa todos los campos"

            !emailRegex.matches(emailValue) ->
                _errorMessage.value = "Ingresa un correo válido terminado en .com o .cl"

            passwordValue.length < 8 ->
                _errorMessage.value = "La contraseña debe tener al menos 8 caracteres"

            !passwordValue.any { it.isUpperCase() } ->
                _errorMessage.value = "La contraseña debe incluir al menos 1 letra mayúscula"

            !passwordValue.any { it.isDigit() } ->
                _errorMessage.value = "La contraseña debe incluir al menos 1 número"

            else -> {
                _errorMessage.value = ""
                viewModelScope.launch {
                    sessionManager.saveUserEmail(emailValue)
                }
                onSuccess()
            }
        }
    }
}
