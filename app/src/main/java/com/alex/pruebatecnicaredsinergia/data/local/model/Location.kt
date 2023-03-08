package com.alex.pruebatecnicaredsinergia.data.local.model

data class Location(
    var id: Int,
    var idProduct: Int,
    val name: String,
    val type: String,
    var numLettersNameLocation: Int,
    var numLettersTypeLocation: Int,
    var status: Int,
    var ssT: Double,
    var ssN: Double,
    var ssMax: Double
)