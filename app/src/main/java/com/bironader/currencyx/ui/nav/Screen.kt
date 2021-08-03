package com.bironader.currencyx.ui.nav

sealed class Screen(val route: String) {

    object CurrencyExchange : Screen(route = "currencyExchange")
}
