package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.localgo.artelabspa.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onProfileClick: () -> Unit
) {
    val externalProduct = viewModel.externalProductTitle.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Bienvenido a Artelab SPA",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        // 🔵 BOTÓN PARA CARGAR API EXTERNA
        Button(onClick = { viewModel.loadExternalProduct() }) {
            Text("Obtener producto externo")
        }

        Spacer(Modifier.height(16.dp))

        // 🔵 Texto con el resultado
        Text(
            text = externalProduct.value,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(32.dp))

        Button(onClick = onProfileClick) {
            Text("Ir a perfil")
        }
    }
}
