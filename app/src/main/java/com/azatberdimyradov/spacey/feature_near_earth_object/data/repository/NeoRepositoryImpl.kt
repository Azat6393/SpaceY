package com.azatberdimyradov.spacey.feature_near_earth_object.data.repository

import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.NeoApi
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NeoDto
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NeoResponseDto
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.repository.NeoRepository
import javax.inject.Inject

class NeoRepositoryImpl @Inject constructor(
    private val api: NeoApi
): NeoRepository{
    override suspend fun getNeo(start_date: String, end_date: String): NeoResponseDto {
        return api.getNeo(start_date, end_date, Constants.NASA_API_KEY)
    }

    override suspend fun getAsteroid(asteroid_id: String): NeoDto {
        return api.getAsteroid(asteroid_id, Constants.NASA_API_KEY)
    }
}