package com.loki.fiats.data.remote.dto

data class DataSearch(
    val coins: List<CoinSearch>,
    val exchanges: List<Exchange>,
    val markets: List<Market>
)