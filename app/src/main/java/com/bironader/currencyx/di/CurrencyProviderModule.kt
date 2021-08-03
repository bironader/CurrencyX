package com.bironader.currencyx.di

import com.bironader.currencyx.datasource.remote.CurrencyApi
import com.bironader.currencyx.datasource.remote.abstraction.CurrencyDataSource
import com.bironader.currencyx.datasource.remote.impl.CurrencyDataSourceImpl
import com.bironader.currencyx.datasource.remote.repoImpl.CurrencyRepoImpl
import com.bironader.currencyx.datasource.remote.responses.CurrencyMapper
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
    fun provideCurrencyDataSource(currencyApi: CurrencyApi): CurrencyDataSource =
        CurrencyDataSourceImpl(currencyApi)


    @Singleton
    @Provides
    fun provideCurrencyMapper(): CurrencyMapper =
        CurrencyMapper()

    @Singleton
    @Provides
    fun provideCurrencyRepo(
        currencyDataSource: CurrencyDataSource,
        mapper: CurrencyMapper
    ): CurrencyRepo =
        CurrencyRepoImpl(currencyDataSource, mapper)

    @Singleton
    @Provides
    fun provideCurrencyUseCase(
        repo: CurrencyRepo,
    ): CurrencyUseCase =
        CurrencyUseCaseImpl(repo)
}