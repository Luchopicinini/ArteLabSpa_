package com.localgo.artelabspa.data.repository

import com.localgo.artelabspa.data.local.SessionManager

class UserRepository(private val sessionManager: SessionManager) {

    suspend fun login(email: String, password: String): Boolean {
        // Simulación para pruebas
        return if (email == "admin@artelab.com" && password == "1234") {
            sessionManager.saveAuthToken("fake-token-123")
            true
        } else {
            false
        }
    }

    suspend fun logout() {
        sessionManager.clearAuthToken()
    }

    suspend fun isUserLoggedIn(): Boolean {
        return sessionManager.getAuthToken() != null
    }
}
