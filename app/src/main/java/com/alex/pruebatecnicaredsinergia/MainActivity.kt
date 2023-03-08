package com.alex.pruebatecnicaredsinergia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alex.pruebatecnicaredsinergia.data.local.model.Storage
import com.alex.pruebatecnicaredsinergia.data.model.*
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity {
    private lateinit var locationList: ArrayList<Location>
    private lateinit var productList: ArrayList<Product>
    //Este metodo permite leer archivo Json que se encuentra localmente y devuelve la información a un modelo de datos de Kotlin
    private fun loadStorageEntity(file: String): Storage {
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        var jsonString = loader.readText()
        loader.close()
        return Gson().fromJson(jsonString, Storage::class.java)
    }

    //Este metodo permite obtener los productos del archivo json
    private fun getProductsFromJson(): ArrayList<Product> {
        val dataFileJson = loadStorageEntity("data.json")
        return dataFileJson.products as ArrayList<Product>
    }

    //Este metodo permite obtener las locaciones del archivo json
    private fun getLocationsFromJson(): ArrayList<Location> {
        val dataFileJson = loadStorageEntity("data.json")
        return dataFileJson.locations as ArrayList<Location>
    }

    //Este metodo asigna a cada locación un ID
    private fun assignLocationId(): ArrayList<Location> {
        locationList = getLocationsFromJson()

        for (i in locationList.indices) {
            locationList[i].id = i + 1
        }

        return locationList
    }

    //Este metodo asigna a cada producto un ID
    private fun assignProductId(): ArrayList<Product> {
        productList = getProductsFromJson()

        for (i in productList.indices) {
            productList[i].id = i + 1
        }

        return productList
    }

    //Este metodo calcula el numero de letras con respecto al nombre y tipo de locación
    private fun getNumLettersLocations(): ArrayList<Location> {
        val locationList = assignLocationId()

        for (location in locationList) {
            val numLettersName = location.name.filter { !it.isWhitespace() }.length
            val numLettersType = location.type.filter { !it.isWhitespace() }.length


            location.numLettersNameLocation = numLettersName
            location.numLettersTypeLocation = numLettersType
        }

        return locationList
    }

    private fun calculateBaseAptitude() {
        for (location in locationList) {
            val ssNumName = location.numLettersNameLocation * 1.5
            location.ssN = ssNumName

            val ssNumeType = location.numLettersTypeLocation * 1.0
            location.ssT = ssNumeType
        }
    }

    private fun calculateBaseAptitudeBonus(
        locationList: ArrayList<Location>,
        productList: ArrayList<Product>
    ) {
        for (product in productList) {
            for (location in locationList) {

                if (product.volume == location.numLettersNameLocation) {
                    if (product.weight % 2 == 0) {
                        val ssMax = location.ssN * 1.5
                        location.ssMax = ssMax
                    } else {
                        val ssMax = location.ssT * 1.5
                        location.ssMax = ssMax
                    }
                }
            }
        }
    }

    private fun matchLocationWithProductBonus(
        locationList: ArrayList<Location>,
        productList: ArrayList<Product>
    ) {
        var contador = 0
        var sstTemporal = 0.0
        var idlocation = 0

        for (product in productList) {
            for (location in locationList) {
                if (product.volume == location.numLettersNameLocation) {
                    contador++

                    when {
                        contador == 1 -> {
                            idlocation = location.id
                            sstTemporal = location.ssT

                            product.status = 1
                            location.apply {
                                idProduct = product.id
                                status = 1
                            }
                        }
                        contador >= 2 -> {
                            if (sstTemporal > location.ssT || sstTemporal == location.ssT) {
                                location.apply {
                                    idProduct = product.id
                                    status = 1
                                }

                                for (location in locationList) {
                                    if (location.id == idlocation) {
                                        location.apply {
                                            idProduct = 0
                                            status = 0
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            idlocation = 0
            sstTemporal = 0.0
            contador = 0
        }
    }

    private fun matchLocationWithProductUnassigned(
        locationList: ArrayList<Location>,
        productList: ArrayList<Product>
    ) {
        for (product in productList) {
            for (location in locationList) {
                if (location.status == 0 && product.status == 0) {

                    location.idProduct = product.id
                    location.status = 1
                    product.status = 1

                    if (product.weight % 2 == 0) {
                        location.ssMax = location.ssN
                    } else {
                        location.ssMax = location.ssT
                    }
                }
            }
        }
    }

    private fun matchLocationWithProduct() {
        val locationList = getNumLettersLocations()
        val productList = assignProductId()
        var ssTotal = 0.0

        calculateBaseAptitude()

        //Se calcula la aptitud base con el aumento del 50% con respecto si es Par o Impar obteniendo su SSMAX
        calculateBaseAptitudeBonus(locationList, productList)


        //Se localiza las ubicaciones con bonus y evalua si existe uno o mas locaciones y lo almacena en la mejor locacion
        matchLocationWithProductBonus(locationList, productList)

        //Se asigna los productos faltantes a una locación y se le asigna el valor SSMax dependiendo si es PAR o IMPAR
        matchLocationWithProductUnassigned(locationList, productList)

        //Se imprime la relación de las locaciones y sus respectivod productos y el SS total
        for (location in locationList) {
            for (product in productList) {
                if (location.idProduct == product.id) {
                    Log.d("TAG", "match:Product: ${product.name} - Location: ${location.name}")
                }
            }
            ssTotal += location.ssMax
        }

        //se imprime el SSTOTAL MAXIMO alcanzado
        Log.d("TAG", "SS TOTAL | $ssTotal")

    }

}
