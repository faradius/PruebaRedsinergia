package com.alex.pruebatecnicaredsinergia.data.repository


import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product

interface StorageRepository {
    suspend fun getLocations(): StorageResult<ArrayList<Location>>
    suspend fun getProducts(): StorageResult<ArrayList<Product>>
}