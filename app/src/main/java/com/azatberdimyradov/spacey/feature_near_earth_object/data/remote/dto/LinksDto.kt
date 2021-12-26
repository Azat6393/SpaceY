package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.Links

data class LinksDto(
    val next: String,
    val prev: String,
    val self: String
)

fun LinksDto.toLinks(): Links{
    return Links(
        next = next,
        prev = prev,
        self = self
    )
}