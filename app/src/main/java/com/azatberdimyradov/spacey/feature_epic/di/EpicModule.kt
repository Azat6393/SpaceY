package com.azatberdimyradov.spacey.feature_epic.di

import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.feature_epic.data.remote.EpicApi
import com.azatberdimyradov.spacey.feature_epic.data.repository.EpicRepositoryImpl
import com.azatberdimyradov.spacey.feature_epic.domain.repository.EpicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpicModule {

    @Provides
    @Singleton
    fun provideEpicApi(): EpicApi {
        return Retrofit.Builder()
            .baseUrl(Constants.EPIC_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EpicApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEpicRepository(
        api: EpicApi
    ): EpicRepository {
        return EpicRepositoryImpl(api)
    }
}