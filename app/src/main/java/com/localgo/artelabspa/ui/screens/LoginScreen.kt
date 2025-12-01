package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.*//Layouts (column, cpacer, padding, fillMaxSize).
import androidx.compose.material3.*//textField, Button, Colors, etc
import androidx.compose.runtime.*//estado compose
import androidx.compose.ui.Alignment//center, top, bottom
import androidx.compose.ui.Modifier//modificadores
import androidx.compose.ui.text.input.PasswordVisualTransformation//oculta texto como password
import androidx.compose.ui.text.input.VisualTransformation//transformacion visual (mostrar o ocultar)
import androidx.compose.ui.unit.dp //unidad dp para medidas
import androidx.lifecycle.viewmodel.compose.viewModel//obtener viewmodel en compose
import com.localgo.artelabspa.viewmodel.LoginViewModel // viewmodel de login
import androidx.compose.material.icons.Icons // iconos
import androidx.compose.material.icons.filled.Visibility //icono visibilidad
import androidx.compose.material.icons.filled.VisibilityOff //icono visibilidad apagada
import androidx.compose.ui.text.font.FontWeight //peso de la fuente de texto
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults //botones
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color


// Pantalla principal del Login
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
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
        // Título principal
        Text(
            text = "Bienvenido al Arte",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Artelab SPA",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Input de correo
        TextField(
            value = email,
            onValueChange = viewModel::onEmailChanged,
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Input de contraseña
        TextField(
            value = password,
            onValueChange = viewModel::onPasswordChanged,
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Sign In
        Button(
            onClick = { viewModel.login(onLoginSuccess) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón Registro
        Button(
            onClick = { /* acción de registro */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Registrarse")
        }

        // Mensaje de error
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
