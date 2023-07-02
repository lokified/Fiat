package com.loki.fiats.data.remote.dto

data class CoinSearch(
    val iconUrl: String,
    val name: String,
    val price: String,
    val symbol: String,
    val uuid: String
)

fun CoinSearch.toCoin(): Coin {
    return Coin(
        uuid = uuid,
        name = name,
        iconUrl = iconUrl,
        price = price,
        symbol = symbol
    )
}