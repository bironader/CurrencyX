package com.bironader.currencyx.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bironader.currencyx.datasource.local.entity.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencyEntity: CurrencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencyEntities: List<CurrencyEntity>)

    @Query("SELECT * FROM currencies_table ORDER BY currency_key")
     fun fetchCurrencies(): Flow<List<CurrencyEntity>>
}