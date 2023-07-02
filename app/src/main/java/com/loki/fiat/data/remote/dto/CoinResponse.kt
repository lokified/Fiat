package com.loki.fiat.data.remote.dto

import com.loki.fiats.data.remote.dto.Data

data class CoinResponse(
    val data: Data,
    val status: String
)