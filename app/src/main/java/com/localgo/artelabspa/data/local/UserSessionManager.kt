package com.localgo.artelabspa.data.local

import android.content.Context//contexto
import androidx.datastore.preferences.core.edit //editar y guardar valores
import androidx.datastore.preferences.core.stringPreferencesKey //crea una llave que crea el nombre con la que guardara y leera un String en los datos
import androidx.datastore.preferences.preferencesDataStore //delegante para crear y obtener pregerencias
import kotlinx.coroutines.flow.first//para ller una vez
import kotlinx.coroutines.flow.map//transformar valores emitidos por un Flow

//Basicamente lo que hace SessionManager es guardar y recuperar el token JWT de forma segura

class SessionManager(private val context: Context) { //el atributo usa los elementos del sistema android

    companion object { //objeto compartido
        private val Context.dataStore by preferencesDataStore(name = "session_prefs") //crea y devuelve preferencias a la bd
        private val KEY_AUTH_TOKEN = stringPreferencesKey("auth_token")//guardar/leer token de autenticación
    }


    // Esta funcion sirve para guardad el token de autenticacion del usuario, por ej:
    //el que se obtiene al iniciar sesion
    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_AUTH_TOKEN] = token
        }
    }

    // Esta funcion recupera el token guardado
    suspend fun getAuthToken(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[KEY_AUTH_TOKEN] }
            .first()
    }

    //Elimina el token
    suspend fun clearAuthToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_AUTH_TOKEN)
        }


    }
}