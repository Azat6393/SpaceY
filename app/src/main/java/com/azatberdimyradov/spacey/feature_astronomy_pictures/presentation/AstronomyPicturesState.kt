package com.azatberdimyradov.spacey.feature_astronomy_pictures.presentation

import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.model.AstronomyPictures

data class AstronomyPicturesState(
    val isLoading: Boolean = false,
    val astronomyPictures: List<AstronomyPictures> = emptyList(),
    val error: String = ""
)
