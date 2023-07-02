package com.loki.fiats.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDetail(
    @SerializedName("24hVolume")
    val volume: String,
    val allTimeHigh: AllTimeHigh,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val description: String,
    val fullyDilutedMarketCap: String,
    val iconUrl: String,
    val links: List<Link>,
    val listedAt: Int,
    val lowVolume: Boolean,
    val marketCap: String,
    val name: String,
    val notices: List<Notice>,
    val numberOfExchanges: Int,
    val numberOfMarkets: Int,
    val price: String,
    val priceAt: Int,
    val rank: Int,
    val sparkline: List<String>,
    val supply: Supply,
    val symbol: String,
    val tags: List<String>,
    val uuid: String,
    val websiteUrl: String
)