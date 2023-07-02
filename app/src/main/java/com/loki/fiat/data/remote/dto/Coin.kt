package com.loki.fiat.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("24hVolume")
    val volume: String = "",
    val btcPrice: String = "",
    val change: String = "",
    val coinrankingUrl: String = "",
    val color: String = "",
    val iconUrl: String,
    val listedAt: Int = 0,
    val marketCap: String = "",
    val name: String,
    val price: String? = null,
    val rank: Int = 0,
    val sparkline: List<String> = emptyList(),
    val symbol: String,
    val uuid: String
)