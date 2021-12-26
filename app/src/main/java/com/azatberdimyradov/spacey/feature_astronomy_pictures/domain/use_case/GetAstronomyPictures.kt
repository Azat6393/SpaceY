package com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.use_case

import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_astronomy_pictures.data.remote.dto.toAstronomyPictures
import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.model.AstronomyPictures
import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.repository.AstronomyPicturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAstronomyPictures @Inject constructor(
    private val repository: AstronomyPicturesRepository
) {
    operator fun invoke(
        start_date: String,
        end_date: String
    ): Flow<Resource<List<AstronomyPictures>>> = flow {
        try {
            emit(Resource.Loading<List<AstronomyPictures>>())
            val astronomyPictures = repository.getAstronomyPictures(
                start_date = start_date, end_date = end_date
            ).map { it.toAstronomyPictures() }
            emit(Resource.Success<List<AstronomyPictures>>(astronomyPictures))
        } catch (e: HttpException) {
            val response = e.response()?.errorBody()?.string()?.split('"')
            if (response == null) {
                emit(
                    Resource.Error<List<AstronomyPictures>>(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } else {
                emit(Resource.Error<List<AstronomyPictures>>(response[5]))
            }
        } catch (e: IOException) {
            emit(
                Resource.Error<List<AstronomyPictures>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }
}