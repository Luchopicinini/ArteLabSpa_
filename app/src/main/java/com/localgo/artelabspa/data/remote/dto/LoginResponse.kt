package com.localgo.artelabspa.data.local
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("id") //permite reconocer el usuario internamente
    val id: Int,

    @SerializedName("Usuario") //Se puede mostrar en pantalla
    val username: String,

    @SerializedName("email") //Verifia identidad
    val email: String,

    @SerializedName("Nombre") // Para mostrar perfil
    val firstName: String,

    @SerializedName("Apellido")
    val lastName: String,

    @SerializedName("AccesoToken")
    val accessToken: String,  // Se usa para autenticar todas las peticiones a la API, en este casp se guarda eN SessionManager

    @SerializedName("RefrescarToken")
    val refreshToken: String?  // Opcional - Para renovar el token
)
