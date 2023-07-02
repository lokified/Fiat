package com.loki.fiat.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loki.fiat.presentation.components.CoinItem
import com.loki.fiat.presentation.components.Loading
import com.loki.fiat.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    openScreen: (String) -> Unit
) {

    val state by viewModel.homeState.collectAsStateWithLifecycle()

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
                Loading(isLoading = state.isLoading)
            }

            if (state.errorMessage.isNotBlank()) {
                Loading(message = state.errorMessage)
            }

            if (state.coinList.isNotEmpty()) {

                LazyColumn(contentPadding = PaddingValues(16.dp)) {

                    items(state.coinList) { coin ->
                        CoinItem(
                            coin = coin,
                            modifier = Modifier.padding(vertical = 8.dp),
                            onClick = { openScreen(Screens.DetailScreen.navWithArgs(it.uuid)) }
                        )
                    }
                }
            }
        }
    }
}