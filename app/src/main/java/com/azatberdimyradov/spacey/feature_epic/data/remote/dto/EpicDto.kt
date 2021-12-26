package com.azatberdimyradov.spacey.feature_epic.data.remote.dto

import com.azatberdimyradov.spacey.feature_epic.domain.model.Epic

data class EpicDto(
    val attitude_quaternions: AttitudeQuaternions,
    val caption: String,
    val centroid_coordinates: CentroidCoordinates,
    val coords: Coords,
    val date: String,
    val dscovr_j2000_position: DscovrJ2000PositionX,
    val identifier: String,
    val image: String,
    val lunar_j2000_position: LunarJ2000PositionX,
    val sun_j2000_position: SunJ2000PositionX,
    val version: String
)

fun EpicDto.toEpic(): Epic{
    return Epic(
        date = date,
        image = image,
        identifier = identifier
    )
}
