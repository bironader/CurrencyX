package com.bironader.currencyx.ui.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun CurrenciesDropDown(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    data: Map<String, String>,
//    onCurrencySelected: (Pair<String, String>) -> Unit = Unit
) {
    var selectedText by remember { mutableStateOf(data["USD"]) }
    var isExpanded by remember { mutableStateOf(expanded) }

    Box(modifier = modifier.clickable { isExpanded = !isExpanded }) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .border(2.dp, MaterialTheme.colors.secondary)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {

            selectedText?.let {
                Text(
                    text = it,
                )
            }
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MaterialTheme.typography.body1.color
            )
        }
        DropdownMenu(
            modifier = modifier.fillMaxWidth()
                .fillMaxHeight(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {

            data.forEach { (key, value) ->
                DropdownMenuItem(onClick = {
                    selectedText = value
                    isExpanded = !isExpanded
//                    onCurrencySelected(Pair(key, value))
                }) {
                    Text(text = value)
                }
            }

        }
    }
}





