package com.azatberdimyradov.spacey.feature_near_earth_object.domain.repository

import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NeoDto
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NeoResponseDto

interface NeoRepository {

    suspend fun getNeo(start_date: String, end_date: String): NeoResponseDto

    suspend fun getAsteroid(asteroid_id: String): NeoDto
}