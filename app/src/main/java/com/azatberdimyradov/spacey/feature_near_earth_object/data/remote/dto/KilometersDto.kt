package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.Kilometers

data class KilometersDto(
    val estimated_diameter_max: Double,
    val estimated_diameter_min: Double
)

fun KilometersDto.toKilometers(): Kilometers{
    return Kilometers(
        estimated_diameter_max = estimated_diameter_max,
        estimated_diameter_min = estimated_diameter_min
    )
}