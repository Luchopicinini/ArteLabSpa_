package com.localgo.artelabspa.model

import android.net.Uri//importa el uri

data class ProfileUiState(//estado ui del perfil
    val isLoading: Boolean = true,
    val userName: String = "",
    val userEmail: String = "",
    val avatarUri: Uri? = null
)
