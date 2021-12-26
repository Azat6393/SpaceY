package com.azatberdimyradov.spacey.feature_near_earth_object.domain.use_case

import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.toNeo
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.Neo
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.repository.NeoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAsteroidById @Inject constructor(
    private val repository: NeoRepository
) {
    operator fun invoke(asteroid_id: String): Flow<Resource<Neo>> = flow {
        try {
            emit(Resource.Loading<Neo>())
            val neo = repository.getAsteroid(asteroid_id).toNeo()
            emit(Resource.Success<Neo>(neo))
        }catch (e: HttpException){
            emit(Resource.Error<Neo>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<Neo>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}