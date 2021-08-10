package com.bironader.currencyx.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.bironader.currencyx.datasource.local.CurrencyDatabase
import com.bironader.currencyx.datasource.local.CurrencyDatabase.Companion.DATABASE_NAME
import com.bironader.currencyx.datasource.local.dao.CurrencyDao
import com.bironader.currencyx.datasource.local.dao.ExchangesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomProviderModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CurrencyDatabase =
        databaseBuilder(
            appContext,
            CurrencyDatabase::class.java,
            DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun currencyDao(db: CurrencyDatabase): CurrencyDao = db.currencyDao()

    @Singleton
    @Provides
    fun exchangeDao(db: CurrencyDatabase): ExchangesDao = db.exchangesDao()



}