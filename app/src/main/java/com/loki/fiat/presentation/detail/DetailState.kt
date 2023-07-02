package com.loki.fiat.presentation.detail

import com.loki.fiat.data.remote.dto.CoinDetail

data class DetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val errorMessage: String = ""
)
