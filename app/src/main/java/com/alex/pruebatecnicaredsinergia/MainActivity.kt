package com.alex.pruebatecnicaredsinergia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alex.pruebatecnicaredsinergia.data.model.Location
import com.alex.pruebatecnicaredsinergia.data.model.Product
import com.alex.pruebatecnicaredsinergia.data.model.Storage
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "getLocationsFromJson: ${getLocationsFromJson()}")
        Log.d("TAG", "getProductsFromJson: ${getProductsFromJson()}")
    }

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
    private fun getLocationsFromJson(): ArrayList<Location> {
        val dataFileJson = loadStorageEntity("data.json")
        return dataFileJson.locations as ArrayList<Location>
    }


}