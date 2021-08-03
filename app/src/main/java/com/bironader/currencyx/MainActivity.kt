package com.bironader.currencyx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
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
import com.bironader.currencyx.ui.screens.CurrencyExchangeContent
import com.bironader.currencyx.ui.screens.CurrencyExchangeViewModel
import com.bironader.currencyx.ui.theme.CurrencyxTheme
import com.bironader.currencyx.ui.widgets.CurrencyAppBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val exampleViewModel: CurrencyExchangeViewModel by viewModels()


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
                            composable(route = Screen.CurrencyExchange.route)
                            {
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


@Composable
fun CurrencyExchangeScreen(viewModel: CurrencyExchangeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiStates.collectAsState()

    CurrencyExchangeContent(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp),
        uiState
    )
}

