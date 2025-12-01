package com.localgo.artelabspa.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Cliente principal para FakeStore (no se toca)
    private const val BASE_URL = "https://fakestoreapi.com/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api: ApiService = retrofit.create(ApiService::class.java)


    // --------------------------------------------------------
    //  Cliente Retrofit para conectarse al backend desplegado en Render
    private const val RENDER_BASE_URL = "https://artelabapi.onrender.com/api/"

    private val renderRetrofit = Retrofit.Builder()
        .baseUrl(RENDER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val renderApi: ApiService = renderRetrofit.create(ApiService::class.java)
}
