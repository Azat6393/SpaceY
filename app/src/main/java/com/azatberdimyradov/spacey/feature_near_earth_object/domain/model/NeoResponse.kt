package com.azatberdimyradov.spacey.feature_near_earth_object.domain.model

data class NeoResponse(
    val element_count: Int,
    val links: Links,
    val near_earth_objects: NearEarthObjects
)