package com.mutualmobile.praxis.data.mapper

import com.mutualmobile.praxis.domain.mappers.DomainModel

interface EntityMapper<M : DomainModel, ME : DataModel> {
  fun mapToDomain(entity: ME): M

  fun mapToEntity(model: M): ME
}

open class DataModel
