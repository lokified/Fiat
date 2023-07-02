package com.loki.fiat.data.remote.dto

import com.loki.fiats.data.remote.dto.DataDetail

data class CoinDetailResponse(
    val data: DataDetail,
    val status: String
)