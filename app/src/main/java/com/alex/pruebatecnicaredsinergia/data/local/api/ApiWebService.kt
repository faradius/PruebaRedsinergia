package com.alex.pruebatecnicaredsinergia.data.local.api



import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import retrofit2.http.GET

interface ApiWebService {

    @GET("/locations.json")
    suspend fun getLocations(): ArrayList<Location>

    @GET("/products.json")
    suspend fun getProducts(): ArrayList<Product>
}