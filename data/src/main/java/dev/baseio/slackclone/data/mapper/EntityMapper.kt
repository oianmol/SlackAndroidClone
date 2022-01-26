package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.domain.mappers.DomainModel

interface EntityMapper<M : DomainModel, ME : DataModel> {
  fun mapToDomain(entity: ME): M

  fun mapToEntity(model: M): ME
}

open class DataModel
