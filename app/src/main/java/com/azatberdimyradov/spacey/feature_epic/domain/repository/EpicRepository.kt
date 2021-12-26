package com.azatberdimyradov.spacey.feature_epic.domain.repository

import com.azatberdimyradov.spacey.feature_epic.data.remote.dto.EpicDto

interface EpicRepository {

    suspend fun getEpic(date: String): List<EpicDto>
}