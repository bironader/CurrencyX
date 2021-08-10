package com.bironader.domain.repo

import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.entites.ExchangeDomainModel
import kotlinx.coroutines.flow.Flow

interface CurrencyRepo {

    suspend fun getCurrencies(): Flow<List<CurrencyDomainModel>>
    suspend fun getExchangeRates(source: String): Flow<List<ExchangeDomainModel>>
    suspend fun insertCurrencies(currencies: List<CurrencyDomainModel>)
    suspend fun insertExchangeRates(exchangeRates: List<ExchangeDomainModel>)
    suspend fun fetchCurrenciesFromLocal() : Flow<List<CurrencyDomainModel>>
    suspend fun fetchExchangeFromLocal(source: String) : Flow<List<ExchangeDomainModel>>
    suspend fun clearAllRates();
}