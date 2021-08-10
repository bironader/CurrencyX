package com.bironader.currencyx.datasource.remote.repoImpl

import com.bironader.currencyx.datasource.remote.abstraction.CurrencyDataSource
import com.bironader.currencyx.datasource.CurrencyMapper
import com.bironader.currencyx.datasource.ExchangeMapper
import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.entites.ExchangeDomainModel
import com.bironader.domain.repo.CurrencyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class CurrencyRepoImpl @Inject constructor(
    private val currencyDataSource: CurrencyDataSource,
    private val currencyMapper: CurrencyMapper,
    private val exchangeMapper: ExchangeMapper
) : CurrencyRepo {
    override suspend fun getCurrencies(): Flow<List<CurrencyDomainModel>> {
        return flow {
            val networkResponse = currencyDataSource.getCurrencies()
            emit(currencyMapper.mapFromRemoteModel(networkResponse))
        }
    }

    override suspend fun getExchangeRates(source: String): Flow<List<ExchangeDomainModel>> {
        return flow {
            val networkResponse = currencyDataSource.getExchangeRates(source = source)
            emit(exchangeMapper.mapFromRemoteModel(networkResponse))
        }
    }

    override suspend fun insertCurrencies(currencies: List<CurrencyDomainModel>) =
        currencyDataSource.insertCurrencies(currencyMapper.mapToLocalEntityModel(currencies))

    override suspend fun insertExchangeRates(exchangeRates: List<ExchangeDomainModel>) =
        currencyDataSource.insertExchangeRates(exchangeMapper.mapToLocalEntityModel(exchangeRates))

    override suspend fun fetchCurrenciesFromLocal(): Flow<List<CurrencyDomainModel>> =
        currencyDataSource.fetchCurrenciesFromLocal()
            .map { entityModel -> currencyMapper.mapFromEntityModel(entityModel) }
            .catch {
                Timber.d(it)
            }


    override suspend fun fetchExchangeFromLocal(source: String): Flow<List<ExchangeDomainModel>> =
        currencyDataSource.fetchExchangeRatesFromLocal(source)
            .map { entityModel -> exchangeMapper.mapFromEntityModel(entityModel) }

    override suspend fun clearAllRates() = currencyDataSource.clearAllRates()

}