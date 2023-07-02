package com.loki.fiats.data.remote.dto

import com.loki.fiat.data.remote.dto.Coin
import com.loki.fiat.data.remote.dto.Stats

data class Data(
    val coins: List<Coin>,
    val stats: Stats
)