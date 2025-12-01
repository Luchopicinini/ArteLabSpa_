package com.localgo.artelabspa.data.local

import android.content.Context//añade contexto
import androidx.datastore.preferences.core.edit // editar y guardar valores
import androidx.datastore.preferences.core.stringPreferencesKey// crea una llave que crea el nombre con la que guardará y leera un String en los datos
import androidx.datastore.preferences.preferencesDataStore//delegante para crear y obtener pregerencias
import kotlinx.coroutines.flow.Flow// flujo asincrono/reactivo de datos
import kotlinx.coroutines.flow.map//transformar valores emitidos por un Flow

private val Context.userSessionDataStore by preferencesDataStore("user_session")//crea preferencias para datos

class UserSessionManager(private val context: Context) {//se crea user sesion manager y el atributo context accede al sistema android

    companion object {//objeto compartido de user session manager
    private val KEY_USER_EMAIL = stringPreferencesKey("user_email")
    }

    // Guarda el correo electrónico del usuario
    suspend fun saveUserEmail(email: String) {
        context.userSessionDataStore.edit { prefs ->
            prefs[KEY_USER_EMAIL] = email
        }
    }

    // Lee el correo guardado
    fun getUserEmail(): Flow<String?> {
        return context.userSessionDataStore.data.map { prefs ->
            prefs[KEY_USER_EMAIL]
        }
    }

    // Limpia la sesión (para logout)
    suspend fun clearSession() {
        context.userSessionDataStore.edit { it.clear() }
    }
}
