package com.bironader.domain.entites

interface Mapper<RemoteModel, LocalEntityModel ,DomainModel> {

    fun mapFromRemoteModel(remoteModel: RemoteModel): DomainModel

    fun mapFromEntityModel(entityModel: LocalEntityModel) : DomainModel

    fun mapToLocalEntityModel(domainModel: DomainModel): LocalEntityModel


}