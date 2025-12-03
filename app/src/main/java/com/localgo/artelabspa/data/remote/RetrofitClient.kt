package com.localgo.artelabspa.data.remote

import com.localgo.artelabspa.data.remote.api.ApiService
import com.localgo.artelabspa.data.remote.api.AvatarApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://artelabspa-api.onrender.com/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    fun createAvatarApiService(): AvatarApiService {
        return retrofit.create(AvatarApiService::class.java)
    }
}
