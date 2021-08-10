package com.bironader.currencyx.datasource.remote.abstraction

import com.bironader.currencyx.datasource.local.entity.CurrencyEntity
import com.bironader.currencyx.datasource.local.entity.ExchangeEntity
import com.bironader.currencyx.datasource.remote.responses.ListCurrenciesResponse
import com.bironader.currencyx.datasource.remote.responses.ListExchangesResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyDataSource {

    suspend fun getCurrencies(): ListCurrenciesResponse
    suspend fun getExchangeRates(source: String): ListExchangesResponse
    suspend fun insertCurrencies(currencies: List<CurrencyEntity>)
    suspend fun insertExchangeRates(exchangeRates: List<ExchangeEntity>)
    suspend fun fetchCurrenciesFromLocal(): Flow<List<CurrencyEntity>>
    suspend fun fetchExchangeRatesFromLocal(source: String): Flow<List<ExchangeEntity>>
    suspend fun clearAllRates()
}