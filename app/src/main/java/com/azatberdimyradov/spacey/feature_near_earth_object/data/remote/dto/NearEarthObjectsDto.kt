package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.NearEarthObjects
import com.google.gson.annotations.SerializedName

data class NearEarthObjectsDto(
    var neo_list: List<NeoDto>
)

fun NearEarthObjectsDto.setNeo(neo: List<NeoDto>) {
    this.neo_list = neo
}

fun NearEarthObjectsDto.toNearEarthObjects(): NearEarthObjects {
    return NearEarthObjects(
        neo_list = neo_list.map { it.toNeo() }
    )
}