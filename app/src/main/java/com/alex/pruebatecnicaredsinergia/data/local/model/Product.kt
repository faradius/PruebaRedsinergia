package com.alex.pruebatecnicaredsinergia.data.local.model

data class Product(
    var id: Int,
    var status: Int, //status: Producto ocupado(1) - Producto disponible(0)
    val name: String,
    val volume: Int,
    val weight: Int
)