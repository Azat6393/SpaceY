package com.azatberdimyradov.spacey.feature_people_in_space.domain.use_case

import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_people_in_space.data.remote.dto.toPeopleInSpace
import com.azatberdimyradov.spacey.feature_people_in_space.domain.model.PeopleInSpace
import com.azatberdimyradov.spacey.feature_people_in_space.domain.repository.PeopleInSpaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPeopleInSpace @Inject constructor(
    private val repository: PeopleInSpaceRepository
) {
    operator fun invoke(): Flow<Resource<PeopleInSpace>> = flow {
        try {
            emit(Resource.Loading<PeopleInSpace>())
            val peopleInSpace = repository.getPeopleInSpace().toPeopleInSpace()
            emit(Resource.Success<PeopleInSpace>(peopleInSpace))
        }catch (e: HttpException){
            emit(Resource.Error<PeopleInSpace>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<PeopleInSpace>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}