package com.loki.fiat.data.remote.repository

import com.loki.fiat.data.remote.CoinRankingApi
import com.loki.fiat.util.Resource
import com.loki.fiat.data.remote.dto.Coin
import com.loki.fiat.data.remote.dto.CoinDetail
import com.loki.fiat.data.remote.dto.DataSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinRankingApi
): CoinRepository {

    override suspend fun getCoinList(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading(data = null))
            emit(
                Resource.Success(
                    data = api.getCoins().data.coins
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error("check your internet connection", data = null))
        }
    }

    override suspend fun getCoinDetail(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading(data = null))
            emit(
                Resource.Success(
                    data = api.getCoinDetail(coinId).data.coin
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error("check your internet connection", data = null))
        }
    }

    override suspend fun searchCoin(query: String): Flow<Resource<DataSearch>> = flow {
        try {
            emit(Resource.Loading(data = null))

            emit(
                Resource.Success(
                    data = api.searchCoin(query).data
                )
            )

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error("check your internet connection", data = null))
        }
    }
}