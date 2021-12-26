package com.azatberdimyradov.spacey.feature_astronomy_pictures.data.repository

import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.feature_astronomy_pictures.data.remote.AstronomyPicturesApi
import com.azatberdimyradov.spacey.feature_astronomy_pictures.data.remote.dto.AstronomyPicturesDto
import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.repository.AstronomyPicturesRepository
import javax.inject.Inject

class AstronomyPicturesRepositoryImpl @Inject constructor(
    private val api: AstronomyPicturesApi
): AstronomyPicturesRepository {
    override suspend fun getAstronomyPictures(
        start_date: String,
        end_date: String
    ): List<AstronomyPicturesDto> {
        return api.getAstronomyPictures(
            api_key = Constants.NASA_API_KEY,
            state_date = start_date,
            end_date = end_date
        )
    }
}