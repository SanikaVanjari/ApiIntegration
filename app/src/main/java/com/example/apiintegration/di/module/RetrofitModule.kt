package com.example.apiintegration.di.module

import com.example.apiintegration.api.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpBuilder = OkHttpClient.Builder()
        httpBuilder
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)  /// show all JSON in logCat
        return httpBuilder.build()
    }

    @Singleton
    @Provides
    fun providesRetrofitService(gsonConverterFactory: GsonConverterFactory, client: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(gsonConverterFactory)
        .client(client)
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesGson() : GsonConverterFactory {
        return  GsonConverterFactory
            .create(
                GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
                .create())
    }
}