package com.localgo.artelabspa.data.repository

import com.localgo.artelabspa.data.remote.ApiService
import com.localgo.artelabspa.data.remote.dto.ProductoDto

class ExternalProductRepository(private val api: ApiService) {

    suspend fun getProductos(): List<ProductoDto> {
        val search = api.searchArt()
        val ids = search.objectIDs?.take(20) ?: emptyList() // primeros 20 objetos

        val list = mutableListOf<ProductoDto>()
        for (id in ids) {
            val art = api.getArtDetail(id)
            list.add(
                ProductoDto(
                    id = art.objectID,
                    title = art.title,
                    price = 0.0, // opcional, arte no tiene precio
                    description = "${art.artistDisplayName} (${art.objectDate})",
                    category = art.classification,
                    image = art.primaryImageSmall
                )
            )
        }
        return list
    }
}
