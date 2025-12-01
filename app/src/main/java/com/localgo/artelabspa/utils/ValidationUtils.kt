package com.localgo.artelabspa.utils

object ValidationUtils {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()//verifica que el email coincida con el existente
    }
}
