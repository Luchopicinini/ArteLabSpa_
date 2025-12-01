package com.localgo.artelabspa.data.local

import com.google.gson.annotations.SerializedName


// Declaramos una clase de datos que se usa para enviar info estructurada a la API

data class LoginRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("expiresInMins")
    val expiresInMins: Int = 30  // Token expira en 30 minutos
)