package com.alex.pruebatecnicaredsinergia.data.local.provider

import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import com.alex.pruebatecnicaredsinergia.data.local.model.Storage
import com.google.gson.Gson
import java.io.InputStreamReader

class DataProvider {
    companion object{
        //Este metodo permite leer archivo Json que se encuentra localmente y devuelve la información a un modelo de datos de Kotlin
        private fun loadStorageEntity(file: String): Storage {
            val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
            var jsonString = loader.readText()
            loader.close()
            return Gson().fromJson(jsonString, Storage::class.java)
        }

        //Este metodo permite obtener los productos del archivo json
        fun getProductsFromJson(): ArrayList<Product> {
            val dataFileJson = loadStorageEntity("data.json")
            return dataFileJson.products as ArrayList<Product>
        }

        //Este metodo permite obtener las locaciones del archivo json
        fun getLocationsFromJson(): ArrayList<Location> {
            val dataFileJson = loadStorageEntity("data.json")
            return dataFileJson.locations as ArrayList<Location>
        }
    }
}