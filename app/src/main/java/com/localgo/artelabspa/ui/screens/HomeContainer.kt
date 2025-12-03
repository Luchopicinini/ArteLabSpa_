package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.localgo.artelabspa.ui.navigation.BottomNavBar

@Composable
fun HomeContainer(
    onLogout: () -> Unit
) {

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                onLogout = onLogout
            )
        }
    ) { padding ->

        Column(Modifier.padding(padding)) {

            when (selectedTab) {
                0 -> HomeScreen()
                1 -> ProductosScreen()
                2 -> ProfileScreen(onLogoutClick = onLogout)
            }
        }
    }
}
