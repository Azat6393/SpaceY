package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.CloseApproachData

data class CloseApproachDataDto(
    val close_approach_date: String,
    val close_approach_date_full: String,
    val epoch_date_close_approach: Long,
    val miss_distance: MissDistanceDto,
    val orbiting_body: String,
    val relative_velocity: RelativeVelocityDto
)

fun CloseApproachDataDto.toCloseApproachData(): CloseApproachData{
    return CloseApproachData(
        close_approach_date_full = close_approach_date_full,
        miss_distance = miss_distance.toMissDistance(),
        orbiting_body = orbiting_body,
        relative_velocity = relative_velocity.toRelativeVelocity()
    )
}