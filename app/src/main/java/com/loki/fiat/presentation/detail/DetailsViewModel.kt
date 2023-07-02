package com.loki.fiat.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.fiat.data.remote.repository.CoinRepository
import com.loki.fiat.util.Constants.COIN_ID
import com.loki.fiat.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: CoinRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    init {
        savedStateHandle.get<String>(COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            repository.getCoinDetail(coinId).collect { result ->

                when(result) {

                    is Resource.Success -> {
                        _detailState.value = DetailState(
                            coin = result.data!!
                        )
                    }

                    is Resource.Loading -> {
                        _detailState.value = DetailState(
                            isLoading = true
                        )
                    }

                    is Resource.Error -> {
                        _detailState.value = DetailState(
                            errorMessage = result.message ?: "Something went wrong"
                        )
                    }
                }
            }
        }
    }
}