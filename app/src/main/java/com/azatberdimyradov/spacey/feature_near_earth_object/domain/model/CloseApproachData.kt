package com.azatberdimyradov.spacey.feature_near_earth_object.domain.model

data class CloseApproachData(
    val close_approach_date_full: String,
    val miss_distance: MissDistance,
    val orbiting_body: String,
    val relative_velocity: RelativeVelocity
)