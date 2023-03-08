package com.alex.pruebatecnicaredsinergia.core

sealed class StorageResult<out R>{
    data class Success<T>(val data: T): StorageResult<T>()
    data class Error<out T>(val exception: Exception?): StorageResult<T>()
}
