package com.bironader.currencyx.di

import com.bironader.currencyx.datasource.local.dao.CurrencyDao
import com.bironader.currencyx.datasource.local.dao.ExchangesDao
import com.bironader.currencyx.datasource.remote.CurrencyApi
import com.bironader.currencyx.datasource.remote.abstraction.CurrencyDataSource
import com.bironader.currencyx.datasource.remote.impl.CurrencyDataSourceImpl
import com.bironader.currencyx.datasource.remote.repoImpl.CurrencyRepoImpl
import com.bironader.currencyx.datasource.CurrencyMapper
import com.bironader.currencyx.datasource.ExchangeMapper
import com.bironader.domain.repo.CurrencyRepo
import com.bironader.domain.usecases.abstraction.CurrencyUseCase
import com.bironader.domain.usecases.impl.CurrencyUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class CurrencyProviderModule {

    @Singleton
    @Provides
    fun provideArticlesListApi(retrofit: Retrofit): CurrencyApi =
        retrofit.create(CurrencyApi::class.java)


    @Singleton
    @Provides
    fun provideCurrencyDataSource(
        currencyApi: CurrencyApi,
        currencyDao: CurrencyDao,
        exchangesDao: ExchangesDao
    ): CurrencyDataSource =
        CurrencyDataSourceImpl(currencyApi, currencyDao, exchangesDao)


    @Singleton
    @Provides
    fun provideCurrencyMapper(): CurrencyMapper =
        CurrencyMapper()

    @Singleton
    @Provides
    fun provideExchangeMapper(): ExchangeMapper =
        ExchangeMapper()

    @Singleton
    @Provides
    fun provideCurrencyRepo(
        currencyDataSource: CurrencyDataSource,
        currencyMapper: CurrencyMapper,
        exchangeMapper: ExchangeMapper
    ): CurrencyRepo =
        CurrencyRepoImpl(currencyDataSource, currencyMapper,exchangeMapper)

    @Singleton
    @Provides
    fun provideCurrencyUseCase(
        repo: CurrencyRepo,
    ): CurrencyUseCase =
        CurrencyUseCaseImpl(repo)
}