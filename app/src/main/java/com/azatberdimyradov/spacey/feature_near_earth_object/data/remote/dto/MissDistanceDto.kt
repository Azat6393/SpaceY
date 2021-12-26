package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.MissDistance

data class MissDistanceDto(
    val astronomical: String,
    val kilometers: String,
    val lunar: String,
    val miles: String
)

fun MissDistanceDto.toMissDistance(): MissDistance{
    return MissDistance(
        astronomical = astronomical,
        kilometers = kilometers,
        lunar = lunar,
        miles = miles
    )
}