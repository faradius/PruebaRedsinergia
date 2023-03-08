package com.alex.pruebatecnicaredsinergia.data.repository


import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.datasource.StorageDatasource
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(private val storageDatasource: StorageDatasource): StorageRepository {
    override suspend fun getLocations(): StorageResult<ArrayList<Location>> {
        return storageDatasource.assignLocationId()
    }

    override suspend fun getProducts(): StorageResult<ArrayList<Product>> {
        return storageDatasource.assignProductId()
    }
}