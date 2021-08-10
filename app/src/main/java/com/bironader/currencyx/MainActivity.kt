package com.bironader.currencyx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bironader.currencyx.ui.nav.Screen
import com.bironader.currencyx.ui.screens.CurrencyExchangeViewModel
import com.bironader.currencyx.ui.screens.CurrencyInputContent
import com.bironader.currencyx.ui.screens.ExchangeListContent
import com.bironader.currencyx.ui.theme.CurrencyxTheme
import com.bironader.currencyx.ui.widgets.CurrencyAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            CurrencyxTheme {
                Scaffold(
                    content = {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.CurrencyExchange.route
                        )
                        {
                            composable(route = Screen.CurrencyExchange.route) {
                                CurrencyExchangeScreen()
                            }
                        }
                    },
                    backgroundColor = MaterialTheme.colors.background,
                    topBar = { CurrencyAppBar() }
                )

            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun CurrencyExchangeScreen(viewModel: CurrencyExchangeViewModel = hiltViewModel()) {

    val amount = viewModel.amount.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp)
    ) {
        CurrencyInputContent(
            uiState = viewModel.inputUiState.collectAsState(),
            onSelectSource = { viewModel.onSelectSource(it) },
            selectedSource = viewModel.selectedSource.collectAsState(),
            onAmountChange = { viewModel.onAmountChange(it) },
            amount = amount
        )

        ExchangeListContent(
            uiState = viewModel.exchangeUiState.collectAsState()
            , amount = amount)
    }


}

