package com.alex.pruebatecnicaredsinergia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alex.pruebatecnicaredsinergia.R
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import com.alex.pruebatecnicaredsinergia.data.local.provider.DataProvider.Companion.getLocationsFromJson
import com.alex.pruebatecnicaredsinergia.data.local.provider.DataProvider.Companion.getProductsFromJson

class MainActivity : AppCompatActivity() {

    private lateinit var productList: ArrayList<Product>
    private lateinit var locationList: ArrayList<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "getLocationsFromJson: ${getLocationsFromJson()}")
        Log.d("TAG", "getProductsFromJson: ${getProductsFromJson()}")

//        assignId()
    }

//    private fun assignId(){
//        productList = getProductsFromJson()
//        locationList = getLocationsFromJson()
//
//        for (i in productList.indices){
//            productList[i].id = i + 1
//        }
//
//        for (i in locationList.indices){
//            locationList[i].id = i + 1
//            locationList[i].idProduct = i + 1
//        }
//
//        Log.d("TAG", "assignId: $productList")
//        Log.d("TAG", "assignId: $locationList")
//    }
}