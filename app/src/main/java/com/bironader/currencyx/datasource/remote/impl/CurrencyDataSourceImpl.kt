package com.bironader.currencyx.datasource.remote.impl

import com.bironader.currencyx.datasource.remote.CurrencyApi
import com.bironader.currencyx.datasource.remote.abstraction.CurrencyDataSource
import com.bironader.currencyx.datasource.remote.responses.ListCurrenciesResponse
import javax.inject.Inject


class CurrencyDataSourceImpl @Inject constructor(private val currencyApi: CurrencyApi) :
    CurrencyDataSource {
    override suspend fun getCurrencies(): ListCurrenciesResponse = currencyApi.getCurrencies()
}