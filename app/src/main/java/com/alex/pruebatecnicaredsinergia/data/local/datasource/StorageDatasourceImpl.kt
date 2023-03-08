package com.alex.pruebatecnicaredsinergia.data.local.datasource

import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.DataFromJSON
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageDatasourceImpl(private val api: DataFromJSON) : StorageDatasource {

    //Este metodo permite obtener los productos del archivo json
    override suspend fun getProductsFromJson(): StorageResult<ArrayList<Product>> {
        return withContext(Dispatchers.Default) {
            try {
                val dataFileJson = api.loadStorageEntity("data.json")
                StorageResult.Success(dataFileJson.products as ArrayList<Product>)
            } catch (e: Exception) {
                StorageResult.Error(e)
            }
        }

    }

    //Este metodo permite obtener las locaciones del archivo json
    override suspend fun getLocationsFromJson(): StorageResult<ArrayList<Location>> {
        return withContext(Dispatchers.Default) {
            try {
                val dataFileJson = api.loadStorageEntity("data.json")
                StorageResult.Success(dataFileJson.locations as ArrayList<Location>)
            } catch (e: Exception) {
                StorageResult.Error(e)
            }
        }
    }

    //Este metodo asigna a cada locación un ID
    override suspend fun assignLocationId(): StorageResult<ArrayList<Location>> {
        return withContext(Dispatchers.Default) {
            try {
                val locationList = getLocationsFromJson() as ArrayList<Location>

                for (i in locationList.indices) {
                    locationList[i].id = i + 1
                }
                StorageResult.Success(locationList)
            }catch (e: Exception){
                StorageResult.Error(e)
            }
        }
    }

    //Este metodo asigna a cada producto un ID
    override suspend fun assignProductId(): StorageResult<ArrayList<Product>> {
        return withContext(Dispatchers.Default){
            try {
                val productList = getProductsFromJson() as ArrayList<Product>

                for (i in productList.indices) {
                    productList[i].id = i + 1
                }

                StorageResult.Success(productList)
            }catch (e: Exception){
                StorageResult.Error(e)
            }
        }
    }


}