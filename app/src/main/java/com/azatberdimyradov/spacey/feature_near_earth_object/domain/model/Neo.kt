package com.azatberdimyradov.spacey.feature_near_earth_object.domain.model

data class Neo(
    val absolute_magnitude_h: Double? = null,
    val close_approach_data: List<CloseApproachData>? = null,
    val estimated_diameter: EstimatedDiameter? = null,
    val id: String? = null,
    val is_potentially_hazardous_asteroid: Boolean? = null,
    val is_sentry_object: Boolean? = null,
    val name: String? = null,
    val nasa_jpl_url: String? = null,
    val neo_reference_id: String? = null
)