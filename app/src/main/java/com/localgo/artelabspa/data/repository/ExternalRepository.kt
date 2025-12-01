package com.localgo.artelabspa.data.repository

import com.localgo.artelabspa.data.remote.ApiService
import com.localgo.artelabspa.data.remote.dto.ProductoDto

class ExternalProductRepository(private val api: ApiService) {

    suspend fun getExternalProduct(): ProductoDto {
        return api.getProductos().first()
    }
}
