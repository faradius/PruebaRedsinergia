package com.alex.pruebatecnicaredsinergia.di

import com.alex.pruebatecnicaredsinergia.data.local.datasource.StorageDatasource
import com.alex.pruebatecnicaredsinergia.data.local.datasource.StorageDatasourceImpl
import com.alex.pruebatecnicaredsinergia.data.repository.StorageRepository
import com.alex.pruebatecnicaredsinergia.data.repository.StorageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class StorageModule {
    @Binds
    abstract fun bindStorageDatasource(impl: StorageDatasourceImpl): StorageDatasource

    @Binds
    abstract fun bindStorageRepository(impl: StorageRepositoryImpl): StorageRepository
}