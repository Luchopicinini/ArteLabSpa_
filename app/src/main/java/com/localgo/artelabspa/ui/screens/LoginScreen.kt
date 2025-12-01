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

    // Variable local para mostrar o esconder la contraseña
    var passwordVisible by remember { mutableStateOf(false) }

    // estructura principal de la pantalla: una columna centrada
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la app
        Text(
            text = "Artelab SPA",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(16.dp))


        // Campo de texto para el correo
        TextField(
            value = email,

            onValueChange = viewModel::onEmailChanged,
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para la contraseña con "ojito"
        TextField(
            value = password,
            onValueChange = viewModel::onPasswordChanged,
            label = { Text("Contraseña") },
            singleLine = true,

            visualTransformation = if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            // Icono al final del campo para alternar visibilidad
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                // Botón que cambia el estado de visibilidad al presionarlo
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Boton para iniciar sesión
        Button(
            onClick = { viewModel.login(onLoginSuccess) },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text("Sign in")
        }


// Boton simulador para registro
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .width(100.dp)     // Ancho del botón
                .height(45.dp) // Altura del botón
        ) {
            Text(
                text = "Registro",
                color = Color.White // Color del texto del boton blanco
            )
        }


        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
