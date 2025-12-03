package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.platform.LocalContext

import com.localgo.artelabspa.viewmodel.LoginViewModel
import com.localgo.artelabspa.data.local.SessionManager   // ✅ IMPORT CORRECTO
import com.localgo.artelabspa.data.repository.AuthRepository
import com.localgo.artelabspa.data.remote.RetrofitClient

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {

    val context = LocalContext.current

    // ✅ SessionManager correcto
    val sessionManager = remember { SessionManager(context) }

    val api = remember { RetrofitClient.createApiService() }
    val authRepository = remember { AuthRepository(api, sessionManager) }

    // ViewModel correcto
    val viewModel = remember { LoginViewModel(authRepository, sessionManager) }

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Bienvenido al Arte", style = MaterialTheme.typography.headlineMedium)
        Text("Artelab SPA", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(24.dp))

        TextField(
            value = email,
            onValueChange = viewModel::onEmailChanged,
            label = { Text("Correo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        TextField(
            value = password,
            onValueChange = viewModel::onPasswordChanged,
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(icon, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { viewModel.login(onLoginSuccess) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }

        Spacer(Modifier.height(12.dp))

        TextButton(onClick = onRegisterClick) {
            Text("Registrarse")
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}
