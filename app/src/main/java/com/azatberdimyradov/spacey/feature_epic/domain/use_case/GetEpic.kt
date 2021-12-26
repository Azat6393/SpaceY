package com.azatberdimyradov.spacey.feature_epic.domain.use_case

import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_epic.data.remote.dto.toEpic
import com.azatberdimyradov.spacey.feature_epic.domain.model.Epic
import com.azatberdimyradov.spacey.feature_epic.domain.repository.EpicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEpic @Inject constructor(
    private val repository: EpicRepository
) {
    operator fun invoke(date: String): Flow<Resource<List<Epic>>> = flow {
        try {
            emit(Resource.Loading<List<Epic>>())
            val epic = repository.getEpic(date).map { it.toEpic() }
            emit(Resource.Success<List<Epic>>(epic))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Epic>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Epic>>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}