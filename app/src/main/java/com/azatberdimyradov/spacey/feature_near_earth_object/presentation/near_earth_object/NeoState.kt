package com.azatberdimyradov.spacey.feature_near_earth_object.presentation.near_earth_object

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.NeoResponse

data class NeoState(
    val isLoading: Boolean = false,
    val neo: NeoResponse? = null,
    val error: String = ""
)
