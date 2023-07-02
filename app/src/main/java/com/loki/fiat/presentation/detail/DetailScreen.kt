package com.loki.fiat.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loki.fiat.presentation.components.Loading
import com.loki.fiat.data.remote.dto.CoinDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    popScreen: () -> Unit
) {

    val state by viewModel.detailState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    state.coin?.let {
                        Text(text = it.name, maxLines = 1)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { popScreen() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIos, contentDescription = null)
                    }
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

            state.coin?.let { coin ->

                Column {
                    OverviewCard(coin = coin, modifier = Modifier.padding(16.dp))
                    Row (modifier = Modifier.padding(16.dp)) {
                        coin.tags.forEach {
                            Tag(content = it, modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OverviewCard(
    modifier: Modifier = Modifier,
    coin: CoinDetail
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(.05f)
        )
    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            Text(text = "Overview", style = MaterialTheme.typography.titleMedium)

            OverViewRow(name = "Rank", amount = coin.rank.toString())
            OverViewRow(name = "Market Cap", amount = coin.marketCap)
            OverViewRow(name = "Markets", amount = coin.numberOfMarkets.toString())
            OverViewRow(name = "Exchanges", amount = coin.numberOfExchanges.toString())
            OverViewRow(name = "Volume", amount = coin.volume)
        }
    }
}


@Composable
fun OverViewRow(name: String, amount: String) {

    Row(Modifier.padding(vertical = 8.dp)) {

        Text(text = name, style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = amount, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    content: String
) {

    Box(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.primary.copy(.1f),
            shape = RoundedCornerShape(4.dp)
        ).padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = content, fontSize = 13.sp)
    }

}