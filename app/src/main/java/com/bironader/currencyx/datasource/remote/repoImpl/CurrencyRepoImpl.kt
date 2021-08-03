package com.bironader.currencyx.datasource.remote.repoImpl

import com.bironader.currencyx.datasource.remote.abstraction.CurrencyDataSource
import com.bironader.currencyx.datasource.remote.responses.CurrencyMapper
import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.repo.CurrencyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepoImpl @Inject constructor(
    private val currencyDataSource: CurrencyDataSource,
    private val currencyMapper: CurrencyMapper
) :
    CurrencyRepo {
    override suspend fun getCurrencies(): Flow<CurrencyDomainModel> {
        return flow {
            val networkResponse = currencyDataSource.getCurrencies()
            emit(currencyMapper.mapFromEntity(networkResponse))
        }
    }
}