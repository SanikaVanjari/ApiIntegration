package com.example.apiintegration.api

import com.example.apiintegration.data.PlanetaryData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("planetary/apod?api_key=Kvt8AGvhwGdgfGqJnpOoc3baoNnnl9NE2YCRZ6IJ")
    suspend fun getPlanetaryData() : Response<PlanetaryData>
}
