package com.loki.fiat.presentation.home

import com.loki.fiat.data.remote.dto.Coin

data class HomeState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val errorMessage: String = ""
)
