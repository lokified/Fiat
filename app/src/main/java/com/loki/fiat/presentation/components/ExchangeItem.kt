package com.loki.fiat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.loki.fiat.data.remote.dto.Exchange

@Composable
fun ExchangeItem(
    modifier: Modifier = Modifier,
    exchange: Exchange,
) {

    val url = exchange.iconUrl.replace(".svg", ".png")
    val isRecommended = if (exchange.recommended) "Recommended" else "Not Recommended"

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
                contentDescription = exchange.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = exchange.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.width(200.dp),
                softWrap = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = isRecommended,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}