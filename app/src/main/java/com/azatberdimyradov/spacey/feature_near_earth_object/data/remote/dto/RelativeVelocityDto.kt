package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.RelativeVelocity

data class RelativeVelocityDto(
    val kilometers_per_hour: String,
    val kilometers_per_second: String,
    val miles_per_hour: String
)

fun RelativeVelocityDto.toRelativeVelocity(): RelativeVelocity{
    return RelativeVelocity(
        kilometers_per_hour = kilometers_per_hour,
        kilometers_per_second = kilometers_per_second,
        miles_per_hour = miles_per_hour
    )
}