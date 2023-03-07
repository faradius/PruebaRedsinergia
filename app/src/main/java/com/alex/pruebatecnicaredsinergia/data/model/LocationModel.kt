package com.alex.pruebatecnicaredsinergia.data.model

data class LocationModel(
    var id: Int,
    var idProduct: Int,
    val name: String,
    val type: String,
    var numLettersNameLocation: Int,
    var numLettersTypeLocation: Int,
    var status: Int = 0,
)