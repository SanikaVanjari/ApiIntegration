package com.example.apiintegration.di.module

import com.example.apiintegration.ui.PlanetaryRepositoryImpl
import com.example.apiintegration.api.ApiService
import com.example.apiintegration.ui.PlanetaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService) : PlanetaryRepository {
        return PlanetaryRepositoryImpl(apiService)
    }
}