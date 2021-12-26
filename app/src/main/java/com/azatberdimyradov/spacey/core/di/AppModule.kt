package com.azatberdimyradov.spacey.core.di

import android.content.Context
import com.azatberdimyradov.spacey.core.DownloadImage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDownloadImage(
        @ApplicationContext context: Context
    ): DownloadImage {
        return DownloadImage(context)
    }
}