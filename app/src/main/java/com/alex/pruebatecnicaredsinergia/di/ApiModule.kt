package com.alex.pruebatecnicaredsinergia.di

import com.alex.pruebatecnicaredsinergia.data.local.api.ApiWebService
import com.alex.pruebatecnicaredsinergia.data.local.model.Storage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://prueba-redsinergia-default-rtdb.firebaseio.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesApiClient(retrofit: Retrofit): ApiWebService {
        return retrofit.create(ApiWebService::class.java)
    }
}