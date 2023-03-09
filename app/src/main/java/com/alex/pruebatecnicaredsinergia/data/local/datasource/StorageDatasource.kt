package com.alex.pruebatecnicaredsinergia.data.local.datasource

import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product

interface StorageDatasource {
    suspend fun getProducts(): StorageResult<List<Product>>
    suspend fun getLocations(): StorageResult<List<Location>>
//    suspend fun assignLocationId(): StorageResult<ArrayList<Location>>
//    suspend fun assignProductId(): StorageResult<ArrayList<Product>>
}