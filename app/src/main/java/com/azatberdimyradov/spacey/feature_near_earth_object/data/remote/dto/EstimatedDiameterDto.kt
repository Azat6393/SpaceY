package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.EstimatedDiameter

data class EstimatedDiameterDto(
    val feet: FeetDto,
    val kilometers: KilometersDto,
    val meters: MetersDto,
    val miles: MilesDto
)

fun EstimatedDiameterDto.toEstimatedDiameter(): EstimatedDiameter{
    return EstimatedDiameter(
        kilometers = kilometers.toKilometers()
    )
}
