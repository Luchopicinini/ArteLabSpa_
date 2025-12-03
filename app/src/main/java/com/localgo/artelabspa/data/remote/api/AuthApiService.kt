package com.localgo.artelabspa.data.remote.api

import com.localgo.artelabspa.data.remote.dto.LoginResponse
import com.localgo.artelabspa.data.remote.dto.LoginRequest
import com.localgo.artelabspa.data.remote.dto.RegisterRequest
import com.localgo.artelabspa.data.remote.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

}
