package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Bienvenido a ArteLab", style = MaterialTheme.typography.headlineMedium)
        Text("Explora arte y bienestar", color = MaterialTheme.colorScheme.primary)

        Spacer(Modifier.height(40.dp))

        Button(
            onClick = { /* más adelante: ir a productos */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Ver Productos")
        }

        Spacer(Modifier.height(16.dp))

        OutlinedButton(
            onClick = { /* más adelante: ir a perfil */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Ir al Perfil")
        }
    }
}
