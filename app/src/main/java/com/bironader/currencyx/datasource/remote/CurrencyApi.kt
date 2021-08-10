package com.bironader.currencyx.datasource.remote

import com.bironader.currencyx.datasource.remote.responses.ListCurrenciesResponse
import com.bironader.currencyx.datasource.remote.responses.ListExchangesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/list")
    suspend fun getCurrencies(): ListCurrenciesResponse

    @GET("/live")
    suspend fun getExchanges(@Query("source") source: String): ListExchangesResponse
}