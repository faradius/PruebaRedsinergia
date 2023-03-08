package com.alex.pruebatecnicaredsinergia.data.local

import com.alex.pruebatecnicaredsinergia.data.local.model.Storage
import com.google.gson.Gson
import dagger.Provides
import java.io.InputStreamReader
import javax.inject.Inject

class DataFromJSON @Inject constructor() {
    //Este metodo permite leer archivo Json que se encuentra localmente y devuelve la información a un modelo de datos de Kotlin
    fun loadStorageEntity(file: String): Storage {
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        var jsonString = loader.readText()
        loader.close()
        return Gson().fromJson(jsonString, Storage::class.java)
    }
}