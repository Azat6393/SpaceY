package com.azatberdimyradov.spacey.feature_epic.data.remote

import com.azatberdimyradov.spacey.feature_epic.data.remote.dto.EpicDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpicApi {

    @GET("EPIC/api/natural/date/{date}")
    suspend fun getEpic(
        @Path("date") date: String,
        @Query("api_key") api_key: String
    ): List<EpicDto>
}