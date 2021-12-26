package com.azatberdimyradov.spacey.feature_near_earth_object.presentation.asteroid_details

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.Neo

data class AsteroidState(
    val isLoading: Boolean = false,
    val asteroid: Neo? = null,
    val error: String = ""
)
