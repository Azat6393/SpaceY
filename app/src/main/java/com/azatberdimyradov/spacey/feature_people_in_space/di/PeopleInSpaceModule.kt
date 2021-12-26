package com.azatberdimyradov.spacey.feature_people_in_space.di

import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.feature_people_in_space.data.remote.PeopleInSpaceApi
import com.azatberdimyradov.spacey.feature_people_in_space.data.repository.PeopleInSpaceRepositoryImpl
import com.azatberdimyradov.spacey.feature_people_in_space.domain.repository.PeopleInSpaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PeopleInSpaceModule {

    @Provides
    @Singleton
    fun providePeopleInSpaceApi(): PeopleInSpaceApi {
        return Retrofit.Builder()
            .baseUrl(Constants.PEOPLE_IN_SPACE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PeopleInSpaceApi::class.java)
    }

    @Provides
    @Singleton
    fun providePeopleInSpaceRepository(api: PeopleInSpaceApi): PeopleInSpaceRepository {
        return PeopleInSpaceRepositoryImpl(api)
    }
}