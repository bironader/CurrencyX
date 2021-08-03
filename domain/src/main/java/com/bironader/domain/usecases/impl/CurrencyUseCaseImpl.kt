package com.bironader.domain.usecases.impl

import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.repo.CurrencyRepo
import com.bironader.domain.usecases.abstraction.CurrencyUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyUseCaseImpl @Inject constructor(private val currencyRepo: CurrencyRepo  ) : CurrencyUseCase {

    override suspend fun getCurrencies(): Flow<CurrencyDomainModel> = currencyRepo.getCurrencies()

}