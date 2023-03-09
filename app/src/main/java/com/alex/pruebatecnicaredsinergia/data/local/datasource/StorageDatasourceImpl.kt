package com.alex.pruebatecnicaredsinergia.data.local.datasource

import android.util.Log
import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.api.ApiWebService
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageDatasourceImpl @Inject constructor(private val apiWebService: ApiWebService) : StorageDatasource {
    override suspend fun getProducts(): StorageResult<List<Product>> {
        return withContext(Dispatchers.IO){
            try {
                val productsResult = apiWebService.getProducts()
                StorageResult.Success(productsResult)
            }catch (e: Exception){
                e.printStackTrace()
                StorageResult.Error(e)
            }
        }
    }

    override suspend fun getLocations(): StorageResult<List<Location>> {
        return withContext(Dispatchers.IO){
            try {
                val locationsResult = apiWebService.getLocations()
                StorageResult.Success(locationsResult)
            }catch (e: Exception){
                e.printStackTrace()
                StorageResult.Error(e)
            }
        }
    }


//    //Este metodo asigna a cada locación un ID
//    override suspend fun assignLocationId(): StorageResult<ArrayList<Location>> {
//        return withContext(Dispatchers.Default) {
//            try {
//                val locationList = getLocations() as ArrayList<Location>
//
//                for (i in locationList.indices) {
//                    locationList[i].id = i + 1
//                }
//
//                StorageResult.Success(locationList)
//            }catch (e: Exception){
//                StorageResult.Error(e)
//            }
//        }
//    }
//
//    //Este metodo asigna a cada producto un ID
//    override suspend fun assignProductId(): StorageResult<ArrayList<Product>> {
//        return withContext(Dispatchers.Default){
//            try {
//                val productList = getProducts() as ArrayList<Product>
//
//                for (i in productList.indices) {
//                    productList[i].id = i + 1
//                }
//
//                StorageResult.Success(productList)
//            }catch (e: Exception){
//                StorageResult.Error(e)
//            }
//        }
//    }


}