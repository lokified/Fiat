package com.loki.fiats.data.remote.dto

data class Supply(
    val circulating: String,
    val confirmed: Boolean,
    val max: String,
    val supplyAt: Int,
    val total: String
)