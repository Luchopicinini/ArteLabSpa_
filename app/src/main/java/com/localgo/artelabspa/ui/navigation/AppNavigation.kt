package com.localgo.artelabspa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// IMPORTS CORRECTOS (TU PROYECTO)
import com.localgo.artelabspa.ui.screens.HomeScreen
import com.localgo.artelabspa.ui.screens.ProfileScreen
import com.localgo.artelabspa.ui.screens.LoginScreen
import com.localgo.artelabspa.viewmodel.HomeViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // LOGIN
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // evita volver al login
                    }
                }
            )
        }

        // HOME
        composable("home") {
            val homeViewModel: HomeViewModel = viewModel()

            HomeScreen(
                viewModel = homeViewModel,
                onProfileClick = {
                    navController.navigate("profile")
                }
            )
        }

        // PROFILE
        composable("profile") {
            ProfileScreen()
        }
    }
}
