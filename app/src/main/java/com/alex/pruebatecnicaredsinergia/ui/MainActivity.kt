package com.alex.pruebatecnicaredsinergia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alex.pruebatecnicaredsinergia.R
import com.alex.pruebatecnicaredsinergia.data.local.provider.DataProvider.Companion.getLocationsFromJson
import com.alex.pruebatecnicaredsinergia.data.local.provider.DataProvider.Companion.getProductsFromJson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "getLocationsFromJson: ${getLocationsFromJson()}")
        Log.d("TAG", "getProductsFromJson: ${getProductsFromJson()}")
    }




}