package com.localgo.artelabspa.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun BottomNavBar(
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onLogout: () -> Unit
) {
    NavigationBar {

        tabs.forEachIndexed { index, tab ->

            val icon = when (tab) {
                "HOME" -> Icons.Default.Home
                "PRODUCTOS" -> Icons.Default.ShoppingCart
                "PERFIL" -> Icons.Default.Person
                else -> Icons.Default.Home
            }

            val label = when (tab) {
                "HOME" -> "Inicio"
                "PRODUCTOS" -> "Productos"
                "PERFIL" -> "Perfil"
                else -> tab
            }

            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) }
            )
        }

        // 🔴 Logout SIEMPRE visible
        NavigationBarItem(
            selected = false,
            onClick = onLogout,
            icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Salir") },
            label = { Text("Salir") }
        )
    }
}
