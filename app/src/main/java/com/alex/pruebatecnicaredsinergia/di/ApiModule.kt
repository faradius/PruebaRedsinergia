package com.alex.pruebatecnicaredsinergia.di

import com.alex.pruebatecnicaredsinergia.data.local.model.Storage
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.InputStreamReader
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object ApiModule {
//
//    @Singleton
//    @Provides
//    //Este metodo permite leer archivo Json que se encuentra localmente y devuelve la información a un modelo de datos de Kotlin
//    fun provideLoadStorageEntity(file: String): Storage {
//        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
//        var jsonString = loader.readText()
//        loader.close()
//        return Gson().fromJson(jsonString, Storage::class.java)
//    }
//}