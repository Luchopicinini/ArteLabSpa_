package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.localgo.artelabspa.data.local.SessionManager
import com.localgo.artelabspa.data.remote.RetrofitClient
import com.localgo.artelabspa.data.repository.AuthRepository
import com.localgo.artelabspa.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    val context = LocalContext.current

    val sessionManager = remember { SessionManager(context) }

    val api = remember { RetrofitClient.createApiService() }
    val authRepository = remember { AuthRepository(api, sessionManager) }

    val viewModel = remember { RegisterViewModel(authRepository) }

    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val successMessage by viewModel.successMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Crear cuenta", style = MaterialTheme.typography.headlineMedium)
        Text("ArteLab SPA", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(24.dp))

        TextField(
            value = name,
            onValueChange = viewModel::onNameChanged,
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        TextField(
            value = email,
            onValueChange = viewModel::onEmailChanged,
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        TextField(
            value = password,
            onValueChange = viewModel::onPasswordChanged,
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { viewModel.register(onRegisterSuccess) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = onBackToLogin) {
            Text("Volver al inicio de sesión")
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }

        if (successMessage.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text(successMessage, color = MaterialTheme.colorScheme.primary)
        }
    }
}
