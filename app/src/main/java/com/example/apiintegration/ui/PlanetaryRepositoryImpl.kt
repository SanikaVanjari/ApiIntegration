package com.example.apiintegration.ui

import com.example.apiintegration.api.ApiService
import com.example.apiintegration.ui.PlanetaryRepository
import com.example.apiintegration.data.PlanetaryData
import retrofit2.Response

class PlanetaryRepositoryImpl(
    var apiService: ApiService
): PlanetaryRepository {
    override suspend fun getNasaData(): Response<PlanetaryData> {
        return apiService.getPlanetaryData()
    }
}