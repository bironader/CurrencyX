package com.bironader.currencyx.datasource.remote.abstraction

import com.bironader.currencyx.datasource.remote.responses.ListCurrenciesResponse

interface CurrencyDataSource {

    suspend fun getCurrencies() : ListCurrenciesResponse
}