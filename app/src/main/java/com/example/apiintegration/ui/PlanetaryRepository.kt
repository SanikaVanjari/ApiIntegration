package com.example.apiintegration.ui

import com.example.apiintegration.data.PlanetaryData
import retrofit2.Response

interface PlanetaryRepository {
    suspend fun getNasaData() : Response<PlanetaryData>
}