package com.localgo.artelabspa.data.remote

import com.localgo.artelabspa.data.remote.dto.ProductoDto
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProductos(): List<ProductoDto>
}
