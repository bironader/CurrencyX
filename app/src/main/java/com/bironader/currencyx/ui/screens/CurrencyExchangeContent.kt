package com.bironader.currencyx.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bironader.currencyx.ui.CurrencyExchangeStates
import com.bironader.currencyx.ui.widgets.CurrenciesDropDown
import timber.log.Timber

@Composable
fun CurrencyExchangeContent(
    modifier: Modifier = Modifier,
    uiState: State<CurrencyExchangeStates>,
) {

    Column(
        modifier = modifier,
    ) {
        when (val uiStateValue = uiState.value) {
            is CurrencyExchangeStates.Loading -> {
                Timber.d("loading")
            }
            is CurrencyExchangeStates.CurrenciesFetched ->
                CurrencyInput(currencies = uiStateValue.data.currencies)

            is CurrencyExchangeStates.Error -> TODO()
            else -> Timber.d("UNknows State")
        }


    }
}

@Composable
fun CurrencyInput(currencies: Map<String, String>) {
    var text by remember { mutableStateOf("Hello") }

    Row {
        TextField(
            modifier = Modifier
                .weight(0.5f)
                .clip(MaterialTheme.shapes.small)
                .border(2.dp, MaterialTheme.colors.secondary),
            value = text,
            shape = MaterialTheme.shapes.small,
            onValueChange = { text = it },
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


            )
    }
}