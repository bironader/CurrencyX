package com.bironader.currencyx.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bironader.currencyx.ui.CurrencyViewState
import com.bironader.currencyx.ui.ExchangeStatesViewState
import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.usecases.abstraction.CurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CurrencyExchangeViewModel @Inject constructor(private val currencyUseCase: CurrencyUseCase) :
    ViewModel() {

    var inputUiState = MutableStateFlow<CurrencyViewState>(CurrencyViewState.None)
        private set


    var exchangeUiState = MutableStateFlow<ExchangeStatesViewState>(ExchangeStatesViewState.None)
        private set

    var selectedSource = MutableStateFlow(CurrencyDomainModel("USA", "USD"))
        private set

    var amount = MutableStateFlow("1")
        private set

    init {

        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCase.fetchCurrenciesFromLocal()
                .onStart {
                    inputUiState.value = CurrencyViewState.Loading
                }
                .catch {
                    Timber.d(it)
                    inputUiState.value = CurrencyViewState.Error(it)
                }
                .collect {
                    inputUiState.value = CurrencyViewState.CurrenciesFetched(it)
                }
        }
    }


    fun onSelectSource(currencyDomainModel: CurrencyDomainModel) {
        selectedSource.value = currencyDomainModel
        viewModelScope.launch(Dispatchers.IO) {
            selectedSource.onStart {
                exchangeUiState.value = ExchangeStatesViewState.Loading
            }.flatMapLatest { currencyModel ->
                currencyUseCase.fetchExchangeFromLocal(currencyModel.key)
            }.catch { throwable ->
                Timber.d(throwable)
                exchangeUiState.value = ExchangeStatesViewState.Error(throwable)
            }.onStart {
                exchangeUiState.value = ExchangeStatesViewState.Loading
            }.collect {
                if (it.isEmpty()) {
                    exchangeUiState.value = ExchangeStatesViewState.EmptyRates
                    return@collect
                }
                exchangeUiState.value = ExchangeStatesViewState.ExchangeRatesFetched(it)

            }
        }

    }

    fun onAmountChange(newAmount: String) {
        amount.value = newAmount
    }

}