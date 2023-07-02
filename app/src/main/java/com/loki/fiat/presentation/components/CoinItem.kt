package com.loki.fiat.presentation.components

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.loki.fiat.data.remote.dto.Coin

@Composable
fun CoinItem(
    modifier: Modifier = Modifier,
    coin: Coin,
    onClick: (Coin) -> Unit = {}
) {

    val url = coin.iconUrl.replace(".svg", ".png")

    val changeColor = if(!coin.change.contains("-")) Color(0xFF2CAD58) else Color.Red
    val coinPrice = String.format("%.2f", coin.price?.toDouble())
    val iconTrend = if (!coin.change.contains("-")) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
    val iconTrendColor = if (!coin.change.contains("-"))Color(0xFF2CAD58) else Color.Red

    Box(
        modifier = modifier.fillMaxWidth()
            .clickable { onClick(coin) }
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
                contentDescription = coin.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))
            
            Column {
                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.width(200.dp),
                    softWrap = true
                )
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = "$${coinPrice}",
                    style = MaterialTheme.typography.labelLarge
                )

                if (coin.change.isNotBlank()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${coin.change}%",
                            style = MaterialTheme.typography.labelSmall,
                            color = changeColor
                        )
                        Icon(
                            imageVector = iconTrend,
                            contentDescription = null,
                            tint = iconTrendColor
                        )
                    }
                }

            }
        }
    }
}