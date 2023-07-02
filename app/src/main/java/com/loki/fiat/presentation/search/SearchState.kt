package com.loki.fiat.presentation.search

import com.loki.fiat.data.remote.dto.Coin
import com.loki.fiat.data.remote.dto.Exchange
import com.loki.fiat.data.remote.dto.Market

data class SearchState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val exchangeList: List<Exchange> = emptyList(),
    val marketList: List<Market> = emptyList(),
    val errorMessage: String = ""
)
