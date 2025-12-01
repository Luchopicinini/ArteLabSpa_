package com.localgo.artelabspa.data.remote.dto

data class ProductoDto(
    val id: Int,
    val title: String,     // <-- NO ES "nombre"
    val price: Double,     // <-- NO ES "precio"
    val description: String,
    val category: String,
    val image: String
)
