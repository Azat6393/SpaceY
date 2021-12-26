package com.azatberdimyradov.spacey.feature_people_in_space.presentation

import com.azatberdimyradov.spacey.feature_people_in_space.domain.model.PeopleInSpace

data class PeopleInSpaceState(
    val isLoading: Boolean = false,
    val peopleInSpace: PeopleInSpace? = null,
    val error: String = ""
)
