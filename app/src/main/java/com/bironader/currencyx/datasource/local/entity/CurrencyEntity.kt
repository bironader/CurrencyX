package com.bironader.currencyx.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "currencies_table")
data class CurrencyEntity(
    @PrimaryKey
    @ColumnInfo(name = "currency_key")
    val key: String = "",
    @ColumnInfo(name = "currency_country")
    val country: String = ""
)
