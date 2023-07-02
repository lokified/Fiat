package com.loki.fiat.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loki.fiat.presentation.components.CoinItem
import com.loki.fiat.presentation.components.ExchangeItem
import com.loki.fiat.presentation.components.Loading
import com.loki.fiat.presentation.components.MarketItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    openScreen: (String) -> Unit
) {
    
    val state by viewModel.searchState.collectAsStateWithLifecycle()

    var term by remember { mutableStateOf("") }
    
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cryptos")
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            if (state.isLoading) {
                Loading(isLoading = state.isLoading, message = "")
            }

            if (state.errorMessage.isNotBlank()) {
                Loading(message = state.errorMessage)
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                SearchSection(
                    onTermChange = {
                        term = it
                    },
                    term = term,
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        viewModel.search(term)
                    }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    if (state.coinList.isNotEmpty()) {
                        item {
                            HeaderSection(content = "Coins", modifier = Modifier.padding(vertical = 8.dp))
                        }

                        items(state.coinList) { coin ->
                            CoinItem(
                                coin = coin,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                    if (state.exchangeList.isNotEmpty()) {
                        item {
                            HeaderSection(content = "Exchanges", modifier = Modifier.padding(vertical = 8.dp))
                        }

                        items(state.exchangeList) { exchange ->
                            ExchangeItem(
                                exchange = exchange,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }


                    if (state.marketList.isNotEmpty()) {
                        item {
                            HeaderSection(content = "Markets", modifier = Modifier.padding(vertical = 8.dp))
                        }

                        items(state.marketList) { market ->
                            MarketItem(
                                market = market,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    content: String
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(text = content, fontSize = 16.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    onTermChange: (String) -> Unit,
    term: String,
    onClick: () -> Unit
) {

    Box(modifier = modifier) {

        TextField(
            value = term,
            onValueChange = { onTermChange(it) },
            placeholder = {
                Text(
                    text = "Enter keywords"
                )
            },
            trailingIcon = {
                IconButton(onClick = { onClick()}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}