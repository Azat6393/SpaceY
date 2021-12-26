package com.azatberdimyradov.spacey.feature_near_earth_object.domain.use_case

import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.model.AstronomyPictures
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.toNeoResponse
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.NeoResponse
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.repository.NeoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNeo @Inject constructor(
    private val repository: NeoRepository
) {
    operator fun invoke(start_date: String, end_date: String): Flow<Resource<NeoResponse>> = flow {
        try {
            emit(Resource.Loading<NeoResponse>())
            val neoResponse = repository.getNeo(start_date, end_date).toNeoResponse()
            emit(Resource.Success<NeoResponse>(neoResponse))
        } catch (e: HttpException) {
            emit(
                Resource.Error<NeoResponse>(
                    if (e.code() == 400) {
                        "Date Format Exception - Expected format (yyyy-mm-dd) - The Feed date limit is only 7 Days"
                    } else e.localizedMessage ?: "An unexpected error occurred",
                ),
            )
        } catch (e: IOException) {
            emit(Resource.Error<NeoResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}