package com.alex.pruebatecnicaredsinergia.data.local.datasource

import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product

interface StorageDatasource {
    suspend fun getProductsFromJson(): StorageResult<ArrayList<Product>>
    suspend fun getLocationsFromJson(): StorageResult<ArrayList<Location>>
    suspend fun assignLocationId(): StorageResult<ArrayList<Location>>
    suspend fun assignProductId(): StorageResult<ArrayList<Product>>
}