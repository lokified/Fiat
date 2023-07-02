package com.loki.fiat.data.remote.dto

import com.loki.fiats.data.remote.dto.CoinSearch

data class DataSearch(
    val coins: List<CoinSearch>,
    val exchanges: List<Exchange>,
    val markets: List<Market>
)