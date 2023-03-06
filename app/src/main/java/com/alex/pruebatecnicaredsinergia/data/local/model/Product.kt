package com.alex.pruebatecnicaredsinergia.data.local.model

data class Product(
    var id: Int,
    val name: String,
    val volume: Int,
    val weight: Int,
    val numLettersNameLocation: Int,
    val numLettersTypeLocation: Int
)