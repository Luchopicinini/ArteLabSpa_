package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.localgo.artelabspa.viewmodel.WeatherViewModel

@Composable
fun HomeScreen(
    onNavigateToProducts: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    // 👉 ViewModel del clima
    val weatherViewModel = remember { WeatherViewModel() }
    val weather by weatherViewModel.weather.collectAsState()

    // 👉 Cargar clima al entrar
    LaunchedEffect(Unit) {
        weatherViewModel.loadWeather("Santiago")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // ---------- BIENVENIDA ----------
        Text(
            text = "Bienvenido a ArteLab",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Explora arte y bienestar",
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(24.dp))

        // ---------- CLIMA ----------
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Clima actual 🌤️",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = if (weather.isNotEmpty()) weather else "Cargando clima...",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(Modifier.height(40.dp))

        // ---------- BOTÓN PRODUCTOS ----------
        Button(
            onClick = onNavigateToProducts,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Ver Productos")
        }

        Spacer(Modifier.height(16.dp))

        // ---------- BOTÓN PERFIL ----------
        OutlinedButton(
            onClick = onNavigateToProfile,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Ir al Perfil")
        }
    }
}
