package com.bironader.currencyx.ui.screens

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bironader.currencyx.ui.CurrencyExchangeStates
import com.bironader.domain.usecases.abstraction.CurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CurrencyExchangeViewModel @Inject constructor(private val currencyUseCase: CurrencyUseCase) :
    ViewModel() {



    var uiStates = MutableStateFlow<CurrencyExchangeStates>(CurrencyExchangeStates.None)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCase.getCurrencies()
                .onStart {
                    uiStates.value = CurrencyExchangeStates.Loading
                }
                .catch {
                    Timber.d(it.toString())
                }.collect {
                    uiStates.value = CurrencyExchangeStates.CurrenciesFetched(it)
                }
        }
    }


}