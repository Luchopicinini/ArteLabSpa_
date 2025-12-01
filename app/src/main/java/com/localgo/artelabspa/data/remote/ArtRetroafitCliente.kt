package com.localgo.artelabspa.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArtRetrofitClient {

    private const val MET_BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(MET_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val metApi: ApiService = retrofit.create(ApiService::class.java)
}
