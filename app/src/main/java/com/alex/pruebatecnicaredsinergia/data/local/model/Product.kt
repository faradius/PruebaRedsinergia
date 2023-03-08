package com.alex.pruebatecnicaredsinergia.data.local.model

data class Product(
    var id: Int,
    var status: Int,
    val name: String,
    val volume: Int,
    val weight: Int
)