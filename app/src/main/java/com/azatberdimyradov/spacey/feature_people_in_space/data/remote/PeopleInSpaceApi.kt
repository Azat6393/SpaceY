package com.azatberdimyradov.spacey.feature_people_in_space.data.remote

import com.azatberdimyradov.spacey.feature_people_in_space.data.remote.dto.PeopleInSpaceDto
import retrofit2.http.GET

interface PeopleInSpaceApi {

    @GET("astros.json")
    suspend fun getPeopleInSpace(): PeopleInSpaceDto

}