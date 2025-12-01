package com.localgo.artelabspa.data.remote.dto

data class ProductoDto(
    val id: Int,
    val title: String,       // el nombre del cuadro
    val price: Double,       // podemos poner 0.0 si no hay precio
    val description: String, // puede ser fecha + artista
    val category: String,    // tipo de objeto (ej. Painting, Sculpture)
    val image: String        // URL de la imagen
)
