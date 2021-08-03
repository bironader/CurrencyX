package com.bironader.currencyx.ui

import com.bironader.domain.entites.CurrencyDomainModel

sealed class CurrencyExchangeStates {

    object None : CurrencyExchangeStates()
    object Loading : CurrencyExchangeStates()
    data class Error(var errorMsg: String) : CurrencyExchangeStates()
    data class CurrenciesFetched(val data: CurrencyDomainModel) : CurrencyExchangeStates()
}
