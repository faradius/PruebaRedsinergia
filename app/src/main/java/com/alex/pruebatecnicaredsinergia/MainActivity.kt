package com.alex.pruebatecnicaredsinergia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alex.pruebatecnicaredsinergia.data.model.*
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var locationList: ArrayList<Location>
    private lateinit var productList: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Log.d("TAG", "getLocationsFromJson: ${getLocationsFromJson()}")
//        Log.d("TAG", "getProductsFromJson: ${getProductsFromJson()}")

//        assignLocationId()

//        testCountLocations()
        matchLocationWithProduct3()
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

    private fun assignLocationId(): ArrayList<Location>{
        locationList = getLocationsFromJson()

        for (i in locationList.indices){
            locationList[i].id = i + 1
        }

        return locationList
    }

    private fun assignProductId(): List<Product>{
        productList = getProductsFromJson()

        for (i in productList.indices){
            productList[i].id = i + 1
        }

        return productList
    }

    private fun getNumLettersLocations(): ArrayList<Location>{
        val locationList = assignLocationId()
        val locationWithNewData = ArrayList<Location>()

        for (location in locationList) {
            val numLettersName = location.name.filter { !it.isWhitespace() }.length
            val numLettersType = location.type.filter { !it.isWhitespace() }.length


            location.numLettersNameLocation = numLettersName
            location.numLettersTypeLocation = numLettersType

            locationWithNewData.add(location)
        }

        return locationWithNewData

//        for (product in productsList.indices){
//            for (location in locationWithNewData.indices){
//                if (productsList[product].volume == locationWithNewData[location].numLettersNameLocation){
//                    Log.d("TAG", "locationWithNewDataMatch: El 'volumen' del producto[$product] coincide con el 'numero de letras'[$location]")
//
//                }
//            }
//        }

    }

    private fun matchLocationWithProduct(){
        val locationList = getNumLettersLocations()
        val productList = assignProductId()
        val locationWithNewData = ArrayList<Location>()

        for (product in productList){
            for (location in locationList){

                if (product.volume == location.numLettersNameLocation){

                    if (product.weight % 2 == 0){
                        val ss = location.numLettersNameLocation * 1.5
                        location.ssN = ss
                    }else{
                        val ss = location.numLettersTypeLocation * 1.0
                        location.ssT = ss
                    }
//                    location.ss *= 1.50

                    locationWithNewData.add(location)
                  Log.d("TAG", "locationWithNewDataMatch: El 'numero de letras'[$location] coincide con el 'volumen' del producto[$product]")
                }
            }
        }

        Log.d("TAG", "matchLocationWithProduct: ${locationWithNewData.size}")
//        Log.d("TAG", "locationWithNewDataMatch: El 'numero de letras'[$location] coincide con el 'volumen' del producto[$product]")
//        Log.d("TAG", "matchLocationWithProduct: ${locationWithNewData}")

    }

    private fun matchLocationWithProduct3(){
        val locationList = getNumLettersLocations()
        val productList = assignProductId()
        val locationWithNewData = ArrayList<Location>()
        var contador = 0
        var sstTemporal = 0.0
        var idlocation = 0

        //se calculan los SSN y SST de todas las locaciones
        for (location in locationList){

            var ssn = location.numLettersNameLocation * 1.5
            location.ssN= ssn

            var sst = location.numLettersTypeLocation * 1.0
            location.ssT = sst
        }
         //se calcula el SSF con bonus respecto si es IMPAR o PAR conrespecto al producto y se almacena en SSF
        for (product in productList){
            for (location in locationList){

                if (product.volume == location.numLettersNameLocation){
                    if (product.weight % 2 == 0){ //Es par
                        val ssf = location.ssN * 1.5
                        location.ssF = ssf
                    }else{ //Es impar
                        val ssf = location.ssT * 1.5
                        location.ssF = ssf
                    }
                }
            }
        }


        //se localiza las ubicaciones con bonus y evalua si existe uno o mas locaciones y lo almacena en la mejor locacion
        for (product in productList) {
            for (location in locationList) {
                if (product.volume == location.numLettersNameLocation) {
                    contador++
                    if (contador == 1){
                        idlocation = location.id
                        sstTemporal = location.ssT
                        location.idProduct = product.id
                        product.status = 1
                        location.status = 1
                    }
                }


                if (contador >= 2){

                    if (sstTemporal > location.ssT){
                        location.idProduct = product.id
                        location.status = 1

                        for (location in locationList){
                            if (location.id == idlocation){
                                location.idProduct = 0
                                location.status = 0
//                                product.status = 1
                            }
                        }
                    }

//                    if (sstTemporal < location.ssT){
////                        location.idProduct = 0
////                        location.status = 0
////                            return
////                        for (location in locationList){
////                            if (location.id == idlocation){
//////                                location.idProduct = product.id
//////                                location.status = 1
//////                                product.status = 1
////
//////                                Log.d("VES", "matchLocationWithProduct3: $location")
////                            }
////                        }
//                    }
                    if (sstTemporal == location.ssT){

                        location.idProduct = product.id
                        location.status = 1

                        for (location in locationList){
                            if (location.id == idlocation){
                                location.idProduct = 0
                                location.status = 0
//                                product.status = 1
                            }
                        }
                    }
                    contador = 0
                }

            }
            idlocation = 0
            sstTemporal = 0.0
            contador = 0
        }

        //verifico si existe algun producto disponible y sustituyo el valor SSF por el valor correspondiente si es PAR o IMPAR
        for (product in productList) {
                for (location in locationList) {
                    if(location.status == 0 && product.status == 0){

                        //Log.d("TAG", "locacion disponible: ${location.name} producto disponible ${product.name}")
                        location.idProduct = product.id
                        product.status = 1
                        location.status = 1
                        if (product.weight % 2 == 0){
                            location.ssF = location.ssN
                        }else{
                            location.ssF = location.ssT
                        }
                    }
                }
        }

        //se imprime resultado maxiado
            for (location in locationList) {
                //Log.d("TAG", "IDLOCATION: ${location.id}| locationNAME: ${location.name}| IDPRODUCT: ${location.idProduct} | STATUS: ${location.status}")
                Log.d("TAG", "${location.idProduct} | ${location.name} | ${location.ssF} | ${location.status}")
            }

        //se imprime el SSTOTAL MAXIMO alcanzado
            var ssftotal = 0.0
            for (location in locationList){
                ssftotal += location.ssF
            }
            Log.d("TAG", "SS TOTAL | $ssftotal")
            for (product in productList){
                Log.d("TAG", "IDPRODUCT: ${product.id}| productNAME: ${product.name} | STATUS: ${product.status}")
            }




    }


    private fun matchLocationWithProduct2(){ //Original
        val locationList = getNumLettersLocations()
        val productList = assignProductId()
        val productCount = mutableMapOf<Int, Int>()
        val locationWithNewData = ArrayList<Location>()

        for (product in productList.indices){
            for (location in locationList.indices){
                if (productList[product].volume == locationList[location].numLettersNameLocation){
                  Log.d("TAG", "locationWithNewDataMatch: El 'volumen' del producto[$product] coincide con el 'numero de letras'[$location]")

//                    location.idProduct = product.id

//                    val productId = location.idProduct
//                    if (productCount.containsKey(productId)){
//
//                        val count = productCount[productId]!!
//                        productCount[productId] = count + 1
//                        location.status = count + 1
////                        Log.d("TAG", "locationWithNewDataMatch: El 'volumen' del producto[$product] coincide con el 'numero de letras'[$location]")
//
//                    }else{
//                        productCount[productId] = 1
//                        location.status = 1
//                        locationWithNewData.add(location)
//                    }

                }
            }
        }

        Log.d("TAG", "matchLocationWithProduct: ${locationWithNewData}")

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