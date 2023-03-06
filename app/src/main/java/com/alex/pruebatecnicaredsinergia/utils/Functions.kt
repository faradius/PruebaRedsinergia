package com.alex.pruebatecnicaredsinergia.utils

//fun loadJsonString(file: String): String?{
//    val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
//    jsonString = loader.readText()
//    loader.close()
//    return jsonString
//}

//fun readJsonFileTest(){
//    val reader = loadJsonString("data.json")
//    if (reader?.contains("Televisores") == true){
//        Log.d("TAG", "readJsonFile: Se leyo el JSON")
//    }
//}

//fun readJsonAlmacenes() {
//    val dataFileJson = loadWarehousesEntity("data.json")
//
//    for (product in dataFileJson.products) {
//        if (product.name == "Televisores") {
//            Log.d("TAG", "readJsonFile: ${product.name}")
//        }
//        Log.d("TAG", "readJsonProducts: $product")
//    }
//}



//val products = arrayOf(
//Product(name = "Teléfono móvil", volume = 10, weight = 12),
//Product(name = "Tablet", volume = 5, weight = 13)
//)
//
//val locations = arrayOf(
//Location(name = "Peru", cantidad = 10),
//Location(name = "Rusia", cantidad = 8)
//)


//private fun testPriceProducts(){
//    val productList = getProductsFromJson()
//    val productsWithPrice = ArrayList<Any>()
//
//    for (product in productList) {
//        val productWithPrice = mutableMapOf<String, Any>()
//        val price = (100..1000).random()
//
//        productWithPrice["name"] = product.name
//        productWithPrice["volume"] = product.volume
//        productWithPrice["weight"] = product.weight
//        productWithPrice["price"] = price
//        productsWithPrice.add(productWithPrice)
//    }
//
//    Log.d("TAG", "matchProductsLocations: $productsWithPrice")
//}


//Este metodo valida si es par o impar
//private fun validateProductEvenOdd(){
//    val weightProductList = getWeightProduct()
//
//    for (wieight in weightProductList){
//        if (wieight % 2 == 0){
//            Log.d("TAG", "validateProduct: $wieight es un numero par")
//        }else{
//            Log.d("TAG", "validateProduct: $wieight es un numero impar")
//        }
//    }
//}



//Este metodo permite obtener el numero de letras de cada locacion
//private fun getNumberLettersNamesLocations(){
//    val nameListLocations = getNameLocations()
//
//    for (word in nameListLocations){
//        var numberLetters = 0
//
//        for (letter in word){
//            if (letter != ' ') numberLetters++
//        }
//
//        Log.d("TAG", "getNumberLettersLocations: El número de letras en la palabra '$word' es: $numberLetters")
//    }
//}