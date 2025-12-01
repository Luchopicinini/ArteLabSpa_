package com.localgo.artelabspa.data.remote

import com.localgo.artelabspa.data.remote.dto.ArtSearchResponse
import com.localgo.artelabspa.data.remote.dto.ArtDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun searchArt(
        @Query("q") query: String = "painting"
    ): ArtSearchResponse

    @GET("objects/{id}")
    suspend fun getArtDetail(
        @Path("id") objectId: Int
    ): ArtDetailResponse
}
