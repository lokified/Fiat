package com.loki.fiat.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.fiat.data.remote.repository.CoinRepository
import com.loki.fiat.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            repository.getCoinList().collect { result ->

                when(result) {

                    is Resource.Success -> {
                        _homeState.value = HomeState(
                            coinList = result.data!!
                        )
                    }

                    is Resource.Loading -> {
                        _homeState.value = HomeState(
                            isLoading = true
                        )
                    }

                    is Resource.Error -> {
                        _homeState.value = HomeState(
                            errorMessage = result.message ?: "Something went wrong"
                        )
                    }
                }
            }
        }
    }
}