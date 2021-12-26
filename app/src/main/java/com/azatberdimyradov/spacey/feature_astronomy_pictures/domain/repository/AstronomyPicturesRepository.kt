package com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.repository

import com.azatberdimyradov.spacey.feature_astronomy_pictures.data.remote.dto.AstronomyPicturesDto

interface AstronomyPicturesRepository {

    suspend fun getAstronomyPictures(
        start_date: String,
        end_date: String
    ): List<AstronomyPicturesDto>
}