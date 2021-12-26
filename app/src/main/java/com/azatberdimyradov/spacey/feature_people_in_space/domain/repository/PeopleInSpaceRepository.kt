package com.azatberdimyradov.spacey.feature_people_in_space.domain.repository

import com.azatberdimyradov.spacey.feature_people_in_space.data.remote.dto.PeopleInSpaceDto

interface PeopleInSpaceRepository {

    suspend fun getPeopleInSpace(): PeopleInSpaceDto

}