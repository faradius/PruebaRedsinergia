package com.alex.pruebatecnicaredsinergia.data.local.model

data class Location(
    var id: Int,
    var idProduct: Int,
    val name: String,
    val type: String,
    var numLettersNameLocation: Int,
    var numLettersTypeLocation: Int,
    var status: Int, //status: Locacion ocupado(1) - Locación disponible(0)
    var ssT: Double, //SST: Puntuación base con respecto al numero de letras del tipo del lugar
    var ssN: Double, //SSN: Puntuación base con respecto al numero de letras del nombre del lugar
    var ssMax: Double //SSMax: Puntuación base de aptitud Maximo
)