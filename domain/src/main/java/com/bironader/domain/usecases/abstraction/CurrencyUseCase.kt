package com.bironader.domain.usecases.abstraction

import com.bironader.domain.entites.CurrencyDomainModel
import kotlinx.coroutines.flow.Flow

interface CurrencyUseCase {
    suspend fun getCurrencies() : Flow<CurrencyDomainModel>
}