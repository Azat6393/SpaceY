package com.azatberdimyradov.spacey.feature_astronomy_pictures.di

import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.feature_astronomy_pictures.data.remote.AstronomyPicturesApi
import com.azatberdimyradov.spacey.feature_astronomy_pictures.data.repository.AstronomyPicturesRepositoryImpl
import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.repository.AstronomyPicturesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AstronomyPicturesModule {

    @Provides
    @Singleton
    fun provideAstronomyPicturesApi(): AstronomyPicturesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.ASTRONOMY_PICTURES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AstronomyPicturesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAstronomyRepository(
        api: AstronomyPicturesApi
    ): AstronomyPicturesRepository {
        return AstronomyPicturesRepositoryImpl(api)
    }
}