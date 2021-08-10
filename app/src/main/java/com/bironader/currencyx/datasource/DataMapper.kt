package com.bironader.currencyx.datasource

import com.bironader.currencyx.datasource.local.entity.CurrencyEntity
import com.bironader.currencyx.datasource.local.entity.ExchangeEntity
import com.bironader.currencyx.datasource.remote.responses.ListCurrenciesResponse
import com.bironader.currencyx.datasource.remote.responses.ListExchangesResponse
import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.entites.ExchangeDomainModel
import com.bironader.domain.entites.Mapper

class CurrencyMapper :
    Mapper<ListCurrenciesResponse, List<CurrencyEntity>, List<CurrencyDomainModel>> {


    override fun mapFromRemoteModel(remoteModel: ListCurrenciesResponse): List<CurrencyDomainModel> {
        val domainList = mutableListOf<CurrencyDomainModel>()
        remoteModel.currencies.forEach { (key, value) ->
            domainList.add(CurrencyDomainModel(value, key))
        }
        return domainList
    }

    override fun mapToLocalEntityModel(domainModel: List<CurrencyDomainModel>): List<CurrencyEntity> {
        val entityList = mutableListOf<CurrencyEntity>()
        domainModel.forEach { model ->
            entityList.add(CurrencyEntity(key = model.key, country = model.countryName))
        }
        return entityList
    }

    override fun mapFromEntityModel(entityModel: List<CurrencyEntity>): List<CurrencyDomainModel> {
        val domainList = mutableListOf<CurrencyDomainModel>()
        entityModel.forEach { localModel ->
            domainList.add(
                CurrencyDomainModel(
                    countryName = localModel.country,
                    key = localModel.key
                )
            )
        }
        return domainList
    }


}


class ExchangeMapper :
    Mapper<ListExchangesResponse, List<ExchangeEntity>, List<ExchangeDomainModel>> {
    override fun mapFromRemoteModel(remoteModel: ListExchangesResponse): List<ExchangeDomainModel> {
        val domainList = mutableListOf<ExchangeDomainModel>()
        remoteModel.quotes.forEach { (key, value) ->
            domainList.add(
                ExchangeDomainModel(
                    source = remoteModel.source,
                    target = key.replace(remoteModel.source, ""),
                    rate = value
                )
            )
        }
        return domainList
    }

    override fun mapToLocalEntityModel(domainModel: List<ExchangeDomainModel>): List<ExchangeEntity> {
        val entityList = mutableListOf<ExchangeEntity>()
        domainModel.forEach { model ->
            entityList.add(
                ExchangeEntity(
                    sourceCurrencyKey = model.source,
                    exchangeRate = model.rate,
                    targetCurrencyKey = model.target
                )
            )
        }
        return entityList
    }

    override fun mapFromEntityModel(entityModel: List<ExchangeEntity>): List<ExchangeDomainModel> {
        val domainList = mutableListOf<ExchangeDomainModel>()
        entityModel.forEach { localModel ->
            domainList.add(
                ExchangeDomainModel(
                    source = localModel.sourceCurrencyKey,
                    target = localModel.targetCurrencyKey,
                    rate = localModel.exchangeRate
                )
            )
        }
        return domainList
    }
}






