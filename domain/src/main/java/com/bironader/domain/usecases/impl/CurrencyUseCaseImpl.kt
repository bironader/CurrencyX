package com.bironader.domain.usecases.impl

import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.entites.ExchangeDomainModel
import com.bironader.domain.flattenToList
import com.bironader.domain.repo.CurrencyRepo
import com.bironader.domain.usecases.abstraction.CurrencyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CurrencyUseCaseImpl @Inject constructor(private val currencyRepo: CurrencyRepo) :
    CurrencyUseCase {


    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun syncData() {

        val currencyFlow = currencyRepo.getCurrencies().flattenToList()
        currencyRepo.insertCurrencies(currencyFlow)
        currencyRepo.clearAllRates()
        currencyFlow.asFlow().flatMapMerge {
            currencyRepo.getExchangeRates(it.key)
                .catch { emit(emptyList()) }
                .onEach { currencyRepo.insertExchangeRates(it) }
        }.catch { emit(emptyList()) }.collect { }

    }

    override suspend fun fetchCurrenciesFromLocal(): Flow<List<CurrencyDomainModel>> =
        currencyRepo.fetchCurrenciesFromLocal()

    override suspend fun fetchExchangeFromLocal(source: String): Flow<List<ExchangeDomainModel>> =
        currencyRepo.fetchExchangeFromLocal(source)

}