package com.azatberdimyradov.spacey.feature_people_in_space.data.remote.dto

import com.azatberdimyradov.spacey.feature_people_in_space.domain.model.PeopleInSpace

data class PeopleInSpaceDto(
    val message: String,
    val number: Int,
    val people: List<PeopleDto>
)

fun PeopleInSpaceDto.toPeopleInSpace(): PeopleInSpace{
    return PeopleInSpace(
        message = message,
        number = number,
        people = people.map { it.toPeople() }
    )
}