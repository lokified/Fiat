package com.loki.fiat.data.remote.repository

import com.loki.fiat.util.Resource
import com.loki.fiats.data.remote.dto.Coin
import com.loki.fiats.data.remote.dto.CoinDetail
import com.loki.fiats.data.remote.dto.DataSearch
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoinList(): Flow<Resource<List<Coin>>>

    suspend fun getCoinDetail(coinId: String): Flow<Resource<CoinDetail>>

    suspend fun searchCoin(query: String): Flow<Resource<DataSearch>>
}