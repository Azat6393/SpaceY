package com.azatberdimyradov.spacey.feature_people_in_space.data.remote.dto

import com.azatberdimyradov.spacey.feature_people_in_space.domain.model.People

data class PeopleDto(
    val craft: String,
    val name: String
)

fun PeopleDto.toPeople(): People{
    return People(
        craft = craft,
        name = name
    )
}