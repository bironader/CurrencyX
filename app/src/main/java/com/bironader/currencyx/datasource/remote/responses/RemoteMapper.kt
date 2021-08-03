package com.bironader.currencyx.datasource.remote.responses

import com.bironader.domain.entites.CurrencyDomainModel
import com.bironader.domain.entites.Mapper

class CurrencyMapper : Mapper<ListCurrenciesResponse, CurrencyDomainModel> {
    override fun mapFromEntity(entity: ListCurrenciesResponse): CurrencyDomainModel =
        CurrencyDomainModel(entity.currencies)

    override fun mapToEntity(domainModel: CurrencyDomainModel): ListCurrenciesResponse =
        ListCurrenciesResponse(
            currencies = domainModel.currencies,
            terms = "",
            success = true,
            privacy = ""
        )

}