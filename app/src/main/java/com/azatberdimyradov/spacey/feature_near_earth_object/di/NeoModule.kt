package com.azatberdimyradov.spacey.feature_near_earth_object.di

import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.core.DeserializerJsonNeo
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.NeoApi
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NearEarthObjectsDto
import com.azatberdimyradov.spacey.feature_near_earth_object.data.repository.NeoRepositoryImpl
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.repository.NeoRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NeoModule {

    @Provides
    @Singleton
    fun provideNeoApi(): NeoApi {

        val gson = GsonBuilder()
            .registerTypeAdapter(NearEarthObjectsDto::class.java, DeserializerJsonNeo())
            .create()

        return Retrofit.Builder()
            .baseUrl(Constants.NEO_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NeoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNeoRepository(
        api: NeoApi
    ): NeoRepository {
        return NeoRepositoryImpl(api)
    }
}