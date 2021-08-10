package com.bironader.currencyx.ui

import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.entites.ExchangeDomainModel


sealed class CurrencyViewState  {
    object None : CurrencyViewState()
    object Loading : CurrencyViewState()
    data class Error(var throwable: Throwable) : CurrencyViewState()
    data class CurrenciesFetched(val data: List<CurrencyDomainModel>) : CurrencyViewState()
}

sealed class ExchangeStatesViewState {
    object None : ExchangeStatesViewState()
    object Loading : ExchangeStatesViewState()
    data class Error(var throwable: Throwable) : ExchangeStatesViewState()
    object  EmptyRates : ExchangeStatesViewState()
    data class ExchangeRatesFetched(val data: List<ExchangeDomainModel>) : ExchangeStatesViewState()
}


