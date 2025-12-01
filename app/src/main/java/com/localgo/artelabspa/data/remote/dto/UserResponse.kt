package com.localgo.artelabspa.data.remote.dto

import com.google.gson.annotations.SerializedName



data class UsersResponse(
    @SerializedName("Usuario")
    val users: List<UserDto>,

    @SerializedName("total")
    val total: Int,  // Total de usuarios en la base de datos

    @SerializedName("skip")
    val skip: Int,

    @SerializedName("limite")
    val limit: Int   // Límite de usuarios por página
)