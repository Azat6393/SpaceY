package com.azatberdimyradov.spacey.feature_people_in_space.data.repository

import com.azatberdimyradov.spacey.feature_people_in_space.data.remote.PeopleInSpaceApi
import com.azatberdimyradov.spacey.feature_people_in_space.data.remote.dto.PeopleInSpaceDto
import com.azatberdimyradov.spacey.feature_people_in_space.domain.repository.PeopleInSpaceRepository
import javax.inject.Inject

class PeopleInSpaceRepositoryImpl @Inject constructor(
    private val api: PeopleInSpaceApi
) : PeopleInSpaceRepository {

    override suspend fun getPeopleInSpace(): PeopleInSpaceDto {
        return api.getPeopleInSpace()
    }
}