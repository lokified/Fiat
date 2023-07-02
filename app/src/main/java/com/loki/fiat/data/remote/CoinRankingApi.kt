package com.loki.fiat.data.remote

import com.loki.fiat.data.remote.dto.CoinDetailResponse
import com.loki.fiat.data.remote.dto.CoinResponse
import com.loki.fiat.data.remote.dto.CoinSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinRankingApi {

    @GET("coins")
    suspend fun getCoins(): CoinResponse

    @GET("coin/{uuid}")
    suspend fun getCoinDetail(
        @Path("uuid") coinId: String
    ): CoinDetailResponse

    @GET("search-suggestions")
    suspend fun searchCoin(
        @Query("query") query: String
    ): CoinSearchResponse
}