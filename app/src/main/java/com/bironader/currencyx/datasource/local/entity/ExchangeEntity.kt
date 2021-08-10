package com.bironader.currencyx.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "exchanges_table")
data class ExchangeEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "source_currency_key")
    val sourceCurrencyKey: String,
    @ColumnInfo(name = "target_currency_key")
    val targetCurrencyKey: String,
    @ColumnInfo(name = "exchange_rate")
    val exchangeRate: Double
)

