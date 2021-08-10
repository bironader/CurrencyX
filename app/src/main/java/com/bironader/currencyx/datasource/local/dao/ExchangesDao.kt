package com.bironader.currencyx.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bironader.currencyx.datasource.local.entity.ExchangeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencyEntities: List<ExchangeEntity>)

    @Query(
        """
        SELECT * FROM exchanges_table
        WHERE source_currency_key = :sourceCurrencyKey
        ORDER BY target_currency_key
        """
    )
    fun fetchExchangeRates(sourceCurrencyKey: String): Flow<List<ExchangeEntity>>

    @Query("DELETE FROM exchanges_table")
    suspend fun clearAll()
}