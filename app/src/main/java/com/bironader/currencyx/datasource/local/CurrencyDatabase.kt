package com.bironader.currencyx.datasource.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.bironader.currencyx.datasource.local.dao.CurrencyDao
import com.bironader.currencyx.datasource.local.dao.ExchangesDao
import com.bironader.currencyx.datasource.local.entity.CurrencyEntity
import com.bironader.currencyx.datasource.local.entity.ExchangeEntity


@Database(
    entities = [CurrencyEntity::class, ExchangeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "currenciesDB"
    }
    abstract fun currencyDao(): CurrencyDao
    abstract fun exchangesDao(): ExchangesDao
}