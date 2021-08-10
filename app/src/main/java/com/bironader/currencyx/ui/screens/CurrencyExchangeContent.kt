package com.bironader.currencyx.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.bironader.currencyx.ui.CurrencyViewState
import com.bironader.currencyx.ui.ExchangeStatesViewState
import com.bironader.currencyx.ui.widgets.CurrenciesDropDown
import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.entites.ExchangeDomainModel
import timber.log.Timber

@Composable
fun CurrencyInputContent(
    modifier: Modifier = Modifier,
    uiState: State<CurrencyViewState>,
    onSelectSource: (CurrencyDomainModel) -> Unit,
    selectedSource: State<CurrencyDomainModel>,
    onAmountChange: (String) -> Unit,
    amount: State<String>
) {
    Column(
        modifier = modifier,
    ) {
        when (val uiStateValue = uiState.value) {
            is CurrencyViewState.Loading -> {
                Loading()
            }
            is CurrencyViewState.CurrenciesFetched -> {
                CurrencyInput(
                    currencies = uiStateValue.data,
                    onSelectSource = onSelectSource,
                    selectedSource = selectedSource,
                    onAmountChange = onAmountChange,
                    amount = amount
                )
            }
            is CurrencyViewState.Error -> {
                Timber.d(uiStateValue.throwable)
            }
            is CurrencyViewState.None -> Timber.d("init")
        }


    }
}

@Composable
fun CurrencyInput(
    currencies: List<CurrencyDomainModel>,
    onSelectSource: (CurrencyDomainModel) -> Unit,
    selectedSource: State<CurrencyDomainModel>,
    onAmountChange: (String) -> Unit,
    amount: State<String>

) {
    Row {
        TextField(
            modifier = Modifier
                .weight(0.5f)
                .clip(MaterialTheme.shapes.small)
                .border(2.dp, MaterialTheme.colors.secondary),
            value = amount.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = MaterialTheme.shapes.small,
            onValueChange = { onAmountChange(it) },
            label = { Text(text = "Amount") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.primary
            )
        )
        CurrenciesDropDown(
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 8.dp)
                .weight(1f),
            data = currencies,
            onSelectSource = onSelectSource,
            selectedSource = selectedSource
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun ExchangeListContent(
    modifier: Modifier = Modifier,
    uiState: State<ExchangeStatesViewState>,
    amount: State<String>
) {
    Box(modifier = modifier)
    {

        when (val uiStateValue = uiState.value) {

            is ExchangeStatesViewState.ExchangeRatesFetched -> {

                LazyVerticalGrid(cells = GridCells.Fixed(4), content = {
                    itemsIndexed(uiStateValue.data) { _, item ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            ExchangeRateItem(item, amount.value)
                        }
                    }
                })
            }
            is ExchangeStatesViewState.Loading -> {
                Loading()
            }
        }
    }
}

@Composable
fun ExchangeRateItem(item: ExchangeDomainModel, amount: String) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column {
            if (amount.isNotEmpty() && amount.isDigitsOnly()) {
                Text(text = item.target, Modifier.padding(8.dp))
                Text(
                    text = String.format("%.2f", item.rate.times(amount.toDouble())),
                    Modifier.padding(8.dp)
                )
            }

        }
    }

}

@Composable
fun Loading() {
    CircularProgressIndicator(color = Color.Black)
}

