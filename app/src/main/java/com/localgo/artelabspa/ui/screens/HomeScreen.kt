package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.localgo.artelabspa.data.local.SessionManager
import com.localgo.artelabspa.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onProfileClick: () -> Unit
) {

    val context = LocalContext.current

    // ✅ Correcto: usar SessionManager
    val sessionManager = remember { SessionManager(context) }

    // ✅ ViewModel creado con SessionManager
    val viewModel = remember { HomeViewModel(sessionManager) }

    val email by viewModel.userEmail.collectAsState()
    val role by viewModel.userRole.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Bienvenido", style = MaterialTheme.typography.titleLarge)
        Text("Email: $email")
        Text("Rol: $role")

        Spacer(modifier = Modifier.height(24.dp))

        // 🔵 Ir al perfil
        Button(
            onClick = onProfileClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ir al Perfil")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 🔴 Cerrar sesión
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.logout()
                }
                onLogout()
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Cerrar sesión")
        }
    }
}
