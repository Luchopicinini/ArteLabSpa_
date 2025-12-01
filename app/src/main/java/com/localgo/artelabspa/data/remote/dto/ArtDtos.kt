package com.localgo.artelabspa.data.remote.dto

// Respuesta de búsqueda
data class ArtSearchResponse(
    val total: Int,
    val objectIDs: List<Int>?
)

// Detalle de un objeto de arte
data class ArtDetailResponse(
    val objectID: Int,
    val title: String,
    val artistDisplayName: String,
    val objectDate: String,
    val classification: String,
    val primaryImageSmall: String
)
