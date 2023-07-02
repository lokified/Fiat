package com.loki.fiat.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.fiat.data.remote.repository.CoinRepository
import com.loki.fiat.util.Resource
import com.loki.fiats.data.remote.dto.toCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel(){

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()


     fun search(name: String) {
        viewModelScope.launch {
            repository.searchCoin(name).collect { result ->

                when(result) {

                    is Resource.Success -> {
                        _searchState.value = SearchState(
                            coinList = result.data?.coins?.map { it.toCoin() }!!,
                            exchangeList = result.data.exchanges,
                            marketList = result.data.markets
                        )

                        if (result.data.exchanges.isEmpty() && result.data.markets.isEmpty() && result.data.coins.isEmpty()) {
                            _searchState.value = SearchState(
                                errorMessage = "No search result",
                                coinList = emptyList(),
                                exchangeList = emptyList(),
                                marketList = emptyList()
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _searchState.value = SearchState(
                            isLoading = true
                        )
                    }

                    is Resource.Error -> {
                        _searchState.value = SearchState(
                            errorMessage = result.message ?: "Something went wrong"
                        )
                    }
                }
            }
        }
    }
}