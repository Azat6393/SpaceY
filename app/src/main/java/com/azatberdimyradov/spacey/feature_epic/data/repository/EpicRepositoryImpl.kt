package com.azatberdimyradov.spacey.feature_epic.data.repository

import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.feature_epic.data.remote.EpicApi
import com.azatberdimyradov.spacey.feature_epic.data.remote.dto.EpicDto
import com.azatberdimyradov.spacey.feature_epic.domain.repository.EpicRepository
import javax.inject.Inject

class EpicRepositoryImpl @Inject constructor(
    private val api: EpicApi
) : EpicRepository {

    override suspend fun getEpic(date: String): List<EpicDto> {
        return api.getEpic(date = date, Constants.NASA_API_KEY)
    }
}