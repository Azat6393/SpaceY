package com.azatberdimyradov.spacey.feature_astronomy_pictures.data.remote

import com.azatberdimyradov.spacey.feature_astronomy_pictures.data.remote.dto.AstronomyPicturesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AstronomyPicturesApi {

    @GET("planetary/apod")
    suspend fun getAstronomyPictures(
        @Query("api_key") api_key: String,
        @Query("start_date") state_date: String,
        @Query("end_date") end_date: String
    ): List<AstronomyPicturesDto>
}