package com.alex.pruebatecnicaredsinergia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alex.pruebatecnicaredsinergia.data.model.Location
import com.alex.pruebatecnicaredsinergia.data.model.LocationModel
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

    //Este metodo permite obtener los nombres de las locaciones
    private fun getNameLocations():ArrayList<String> {
        val namesListLocations: ArrayList<String> = ArrayList()
        val locationsEntity = getLocationsFromJson()

        for (i in 0 until locationsEntity.size){
            val location = locationsEntity[i]
            val nameLocation = location.name

            namesListLocations.add(nameLocation)
        }

        return namesListLocations
    }

    //Este metodo permite obtener el numero de letras de cada locacion
    private fun getNumberLettersNamesLocations(){
        val nameListLocations = getNameLocations()

        for (word in nameListLocations){
            var numberLetters = 0

            for (letter in word){
                if (letter != ' ') numberLetters++
            }

            Log.d("TAG", "getNumberLettersLocations: El número de letras en la palabra '$word' es: $numberLetters")
        }
    }

    //Este metodo permite obtener los tipos de las locaciones
    private fun getTypeLocations():ArrayList<String> {
        val typeListLocations: ArrayList<String> = ArrayList()
        val locationsEntity = getLocationsFromJson()

        for (i in 0 until locationsEntity.size){
            val location = locationsEntity[i]
            val nameLocation = location.type

            typeListLocations.add(nameLocation)
        }

        return typeListLocations
    }


    //Este metodo permite obtener el numero de letras de cada tipo de locacion
    private fun getNumberLettersTypesLocations(){
        val typeListLocations = getTypeLocations()

        for (word in typeListLocations){
            var numberLetters = 0

            for (letter in word){
                if (letter != ' ') numberLetters++
            }

            Log.d("TAG", "getNumberLettersLocations: El número de letras en la palabra '$word' es: $numberLetters")
        }
    }

    //Este metodo permite obtener una lista de los pesos
    private fun getWeightProduct():ArrayList<Int>{
        val weightProductList: ArrayList<Int> = ArrayList()
        val productsEntity = getProductsFromJson()

        for (i in 0 until productsEntity.size){
            val product = productsEntity[i]
            val weightProduct = product.weight

            weightProductList.add(weightProduct)
        }

        return weightProductList
    }

    //Este metodo hace la relación de uno a uno de dos arreglos
    private fun matchProductsLocations(){
        val products = getProductsFromJson()
        val locations = getLocationsFromJson()

        for (i in products.indices){
            val product = products[i]
            val location = locations[i]
            Log.d("TAG", "matchProductsLocations: Esta tienda $location tiene $product")
        }
    }

    private fun testPriceProducts(){
        val productList = getProductsFromJson()
        val productsWithPrice = ArrayList<Any>()

        for (product in productList) {
            val productWithPrice = mutableMapOf<String, Any>()
            val price = (100..1000).random()

            productWithPrice["name"] = product.name
            productWithPrice["volume"] = product.volume
            productWithPrice["weight"] = product.weight
            productWithPrice["price"] = price
            productsWithPrice.add(productWithPrice)
        }

        Log.d("TAG", "matchProductsLocations: $productsWithPrice")
    }

    private fun testCountLocations(){
        val locationList = getLocationsFromJson()
        val productsList = getProductsFromJson()
        val locationWithNewData = ArrayList<LocationModel>()

        for (location in locationList) {
            val numLettersName = location.name.filter { !it.isWhitespace() }.length
            val numLettersType = location.type.filter { !it.isWhitespace() }.length

            val locationData = LocationModel(
                name = location.name,
                type = location.type,
                numLettersNameLocation = numLettersName,
                numLettersTypeLocation = numLettersType
            )

            locationWithNewData.add(locationData)
        }

        for (product in productsList.indices){
            for (location in locationWithNewData.indices){
                if (productsList[product].volume == locationWithNewData[location].numLettersNameLocation){
                    Log.d("TAG", "locationWithNewDataMatch: El 'volumen' del producto[$product] coincide con el 'numero de letras'[$location]")

                }
            }
        }

        Log.d("TAG", "locationWithNewData: $locationWithNewData")
//        Log.d("TAG", "locationWithNewData: ")
    }

    private fun testCountLocations2(){
        val locationList = getLocationsFromJson()
        val locationWithNewData = ArrayList<Any>()

        for (location in locationList) {
            val numLettersName = location.name.filter { !it.isWhitespace() }.length
            val numLettersType = location.type.filter { !it.isWhitespace() }.length

            val locationData = mutableMapOf<String, Any>()

            locationData["name"] = location.name
            locationData["type"] = location.type
            locationData["num_letters_name"] = numLettersName
            locationData["num_letters_type"] = numLettersType
            locationWithNewData.add(locationData)
        }

        Log.d("TAG", "locationWithNewData: $locationWithNewData")
    }


    private fun validateWeightProduct(){
        val productList = getProductsFromJson()

        for (product in productList){
            if (product.weight % 2 == 0){
                Log.d("TAG", "validateProduct: ${product.name} = ${product.weight} es un numero par")
            }else{
                Log.d("TAG", "validateProduct: ${product.name} = ${product.weight} es un numero impar")
            }
        }
    }

}