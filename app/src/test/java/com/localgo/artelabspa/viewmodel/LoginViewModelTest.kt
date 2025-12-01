package com.localgo.artelabspa.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.localgo.artelabspa.data.local.UserSessionManager
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain


@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Para LiveData/StateFlow

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var sessionManager: UserSessionManager
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        sessionManager = mockk(relaxed = true)
        // Aquí usamos el constructor que recibe el UserSessionManager y Application
        viewModel = LoginViewModel(sessionManager, mockk(relaxed = true))
    }


    @Test
    fun `login con campos vacíos devuelve error`() = runTest {
        viewModel.onEmailChanged("")
        viewModel.onPasswordChanged("")
        var successCalled = false
        viewModel.login { successCalled = true }
        assertEquals("Completa todos los campos", viewModel.errorMessage.value)
        assert(!successCalled)
    }

    @Test
    fun `login con email inválido devuelve error`() = runTest {
        viewModel.onEmailChanged("email@mal")
        viewModel.onPasswordChanged("Abcdef12")
        var successCalled = false
        viewModel.login { successCalled = true }
        assertEquals("Ingresa un correo válido terminado en .com o .cl", viewModel.errorMessage.value)
        assert(!successCalled)
    }

    @Test
    fun `login con contraseña corta devuelve error`() = runTest {
        viewModel.onEmailChanged("test@domain.com")
        viewModel.onPasswordChanged("Abc12")
        var successCalled = false
        viewModel.login { successCalled = true }
        assertEquals("La contraseña debe tener al menos 8 caracteres", viewModel.errorMessage.value)
        assert(!successCalled)
    }

    @Test
    fun `login sin mayúscula devuelve error`() = runTest {
        viewModel.onEmailChanged("test@domain.com")
        viewModel.onPasswordChanged("abcdef12")
        var successCalled = false
        viewModel.login { successCalled = true }
        assertEquals("La contraseña debe incluir al menos 1 letra mayúscula", viewModel.errorMessage.value)
        assert(!successCalled)
    }

    @Test
    fun `login sin número devuelve error`() = runTest {
        viewModel.onEmailChanged("test@domain.com")
        viewModel.onPasswordChanged("Abcdefgh")
        var successCalled = false
        viewModel.login { successCalled = true }
        assertEquals("La contraseña debe incluir al menos 1 número", viewModel.errorMessage.value)
        assert(!successCalled)
    }

    @Test
    fun `login correcto llama a onSuccess y guarda email`() = runTest {
        viewModel.onEmailChanged("test@domain.com")
        viewModel.onPasswordChanged("Abcdef12")
        var successCalled = false
        viewModel.login { successCalled = true }

        // Avanzamos corrutinas
        testScheduler.advanceUntilIdle()

        assertEquals("", viewModel.errorMessage.value)
        assert(successCalled)
        coVerify { sessionManager.saveUserEmail("test@domain.com") }
    }

}
