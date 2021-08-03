package com.bironader.currencyx.datasource.remote

import com.bironader.currencyx.datasource.remote.responses.ListCurrenciesResponse
import retrofit2.http.GET

interface CurrencyApi {

    @GET("/list")
    suspend fun getCurrencies(): ListCurrenciesResponse
}