package com.bironader.currencyx.datasource.remote.impl

import com.bironader.currencyx.datasource.local.dao.CurrencyDao
import com.bironader.currencyx.datasource.local.dao.ExchangesDao
import com.bironader.currencyx.datasource.local.entity.CurrencyEntity
import com.bironader.currencyx.datasource.local.entity.ExchangeEntity
import com.bironader.currencyx.datasource.remote.CurrencyApi
import com.bironader.currencyx.datasource.remote.abstraction.CurrencyDataSource
import com.bironader.currencyx.datasource.remote.responses.ListCurrenciesResponse
import com.bironader.currencyx.datasource.remote.responses.ListExchangesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CurrencyDataSourceImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val currencyDao: CurrencyDao,
    private val exchangesDao: ExchangesDao
) : CurrencyDataSource {
    override suspend fun getCurrencies(): ListCurrenciesResponse = currencyApi.getCurrencies()

    override suspend fun getExchangeRates(source: String): ListExchangesResponse =
        currencyApi.getExchanges(source)

    override suspend fun insertCurrencies(currencies: List<CurrencyEntity>) =
        currencyDao.insertAll(currencies)

    override suspend fun insertExchangeRates(exchangeRates: List<ExchangeEntity>) =
        exchangesDao.insertAll(exchangeRates)

    override suspend fun fetchCurrenciesFromLocal(): Flow<List<CurrencyEntity>>  = currencyDao.fetchCurrencies()

    override suspend fun fetchExchangeRatesFromLocal(source: String): Flow<List<ExchangeEntity>> =
        exchangesDao.fetchExchangeRates(source)

    override suspend fun clearAllRates() = exchangesDao.clearAll()
}