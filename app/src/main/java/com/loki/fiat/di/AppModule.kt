package com.loki.fiat.di

import com.loki.fiat.BuildConfig
import com.loki.fiat.data.remote.CoinRankingApi
import com.loki.fiat.data.remote.repository.CoinRepository
import com.loki.fiat.data.remote.repository.CoinRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.coinranking.com/v2/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): CoinRankingApi {

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .apply {
                        addHeader("x-access-token", BuildConfig.API_KEY)
                    }.build()
                it.proceed(request)
            }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CoinRankingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApi(api: CoinRankingApi): CoinRepository {

        return CoinRepositoryImpl(api = api)
    }
}