package com.localgo.artelabspa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelabspa.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RegisterViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _successMessage = MutableStateFlow("")
    val successMessage: StateFlow<String> = _successMessage

    fun onNameChanged(newValue: String) {
        _name.value = newValue
    }

    fun onEmailChanged(newValue: String) {
        _email.value = newValue
    }

    fun onPasswordChanged(newValue: String) {
        _password.value = newValue
    }

    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val res = repository.register(
                    nombre = name.value,
                    email = email.value,
                    password = password.value
                )




                if (res.success) {
                    _successMessage.value = "Registro exitoso"
                    onSuccess()
                } else {
                    _errorMessage.value = res.message
                }

            } catch (e: HttpException) {
                _errorMessage.value = "Error HTTP: ${e.code()}"
            } catch (e: IOException) {
                _errorMessage.value = "Sin conexión a Internet"
            } catch (e: Exception) {
                _errorMessage.value = "Error inesperado: ${e.message}"
            }
        }
    }
}
