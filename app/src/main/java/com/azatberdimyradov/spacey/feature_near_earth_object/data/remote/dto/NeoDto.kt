package com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto

import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.Neo

data class NeoDto(
    val absolute_magnitude_h: Double? = null,
    val close_approach_data: List<CloseApproachDataDto>? = null,
    val estimated_diameter: EstimatedDiameterDto? = null,
    val id: String? = null,
    val is_potentially_hazardous_asteroid: Boolean? = null,
    val is_sentry_object: Boolean? = null,
    val links: LinksXDto? = null,
    val name: String? = null,
    val nasa_jpl_url: String? = null,
    val neo_reference_id: String? = null
)

fun NeoDto.toNeo(): Neo {
    return Neo(
        absolute_magnitude_h = absolute_magnitude_h,
        close_approach_data = close_approach_data!!.map { it.toCloseApproachData() },
        estimated_diameter = estimated_diameter!!.toEstimatedDiameter(),
        id = id,
        is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid,
        is_sentry_object = is_sentry_object,
        name = name,
        nasa_jpl_url = nasa_jpl_url,
        neo_reference_id = neo_reference_id
    )
}