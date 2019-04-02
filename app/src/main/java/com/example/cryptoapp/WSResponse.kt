package com.example.cryptoapp

data class WSResponse(
    val price: Double,
    val sequence: Int,
    val size: Double,
    val symbol_id: String,
    val taker_side: String,
    val time_coinapi: String,
    val time_exchange: String,
    val type: String,
    val uuid: String
)