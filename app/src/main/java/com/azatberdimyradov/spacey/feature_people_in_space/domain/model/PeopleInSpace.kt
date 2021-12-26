package com.azatberdimyradov.spacey.feature_people_in_space.domain.model

data class PeopleInSpace(
    val message: String,
    val number: Int,
    val people: List<People>
)