package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote

import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NeoDto
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NeoResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NeoApi {

    @GET("neo/rest/v1/feed")
    suspend fun getNeo(
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query("api_key") api_key: String
    ): NeoResponseDto

    @GET("neo/rest/v1/neo/{asteroid_id}")
    suspend fun getAsteroid(
        @Path("asteroid_id") asteroid_id: String,
        @Query("api_key") api_key: String
    ): NeoDto

}