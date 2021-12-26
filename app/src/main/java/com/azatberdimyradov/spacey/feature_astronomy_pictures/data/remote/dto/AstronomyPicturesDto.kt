package com.azatberdimyradov.spacey.feature_astronomy_pictures.data.remote.dto

import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.model.AstronomyPictures
import com.google.gson.annotations.SerializedName

data class AstronomyPicturesDto(
    val copyright: String? = null,
    val date: String,
    val explanation: String,
    val hdurl: String? = null,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)

fun AstronomyPicturesDto.toAstronomyPictures(): AstronomyPictures{
    return AstronomyPictures(
        date = date,
        explanation = explanation,
        media_type = media_type,
        title = title,
        url = url
    )
}