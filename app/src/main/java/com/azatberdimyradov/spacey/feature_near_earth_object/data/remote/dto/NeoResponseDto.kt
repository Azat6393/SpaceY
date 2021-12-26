package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.NeoResponse

data class NeoResponseDto(
    val element_count: Int,
    val links: LinksDto,
    val near_earth_objects: NearEarthObjectsDto
)

fun NeoResponseDto.toNeoResponse(): NeoResponse{
    return NeoResponse(
        element_count = element_count,
        links = links.toLinks(),
        near_earth_objects = near_earth_objects.toNearEarthObjects()
    )
}