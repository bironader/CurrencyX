package com.bironader.currencyx.ui.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.bironader.domain.entites.CurrencyDomainModel
import timber.log.Timber

@Composable
fun CurrenciesDropDown(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    data: List<CurrencyDomainModel>,
    onSelectSource: (CurrencyDomainModel) -> Unit,
    selectedSource: State<CurrencyDomainModel>
) {


    var isExpanded by remember { mutableStateOf(expanded) }
    onSelectSource(selectedSource.value)
    Box(modifier = modifier.clickable { isExpanded = !isExpanded }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .border(2.dp, MaterialTheme.colors.secondary)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {

            Text(
                text = "(${selectedSource.value.key}) ${selectedSource.value.countryName}"
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MaterialTheme.typography.body1.color
            )
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {

            data.forEach { item ->
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(), onClick = {
                    isExpanded = !isExpanded
                    onSelectSource(item)
                }) {
                    Text(text = "(${item.key}) ${item.countryName}")
                }
                Divider()
            }
        }
    }
}





