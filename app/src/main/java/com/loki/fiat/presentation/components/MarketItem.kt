package com.loki.fiat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.loki.fiat.data.remote.dto.Market

@Composable
fun MarketItem(
    modifier: Modifier = Modifier,
    market: Market,
) {

    val url = market.exchangeIconUrl.replace(".svg", ".png")
    val isRecommended = if (market.recommended) "Recommended" else "Not Recommended"


    Box(
        modifier = modifier.fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(
                vertical = 8.dp
            )
        ) {

            AsyncImage(
                model = url,
                contentDescription = market.exchangeName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = market.exchangeName,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.width(200.dp),
                    softWrap = true
                )
                Text(
                    text = "${market.baseSymbol} - ${market.quoteSymbol}",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = isRecommended,
                    style = MaterialTheme.typography.labelLarge
                )

            }
        }
    }
}