package com.localgo.artelabspa.utils

object ValidationUtils {
    fun isValidEmail(email: String): Boolean {
        val regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|cl)$".toRegex()
        return regex.matches(email)
    }
}
