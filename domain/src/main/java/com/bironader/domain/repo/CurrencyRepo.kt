package com.bironader.domain.repo

import com.bironader.domain.entites.CurrencyDomainModel
import kotlinx.coroutines.flow.Flow

interface CurrencyRepo {

    suspend fun getCurrencies() : Flow<CurrencyDomainModel>

}