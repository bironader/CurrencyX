package com.bironader.domain.usecases.abstraction

import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.entites.ExchangeDomainModel
import kotlinx.coroutines.flow.Flow

interface CurrencyUseCase {
    suspend fun syncData()
    suspend fun fetchCurrenciesFromLocal() : Flow<List<CurrencyDomainModel>>
    suspend fun fetchExchangeFromLocal(source: String) : Flow<List<ExchangeDomainModel>>
}