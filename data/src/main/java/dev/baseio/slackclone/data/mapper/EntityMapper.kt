package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.domain.mappers.DomainModel

interface EntityMapper<Domain : DomainModel, Data : DataModel> {
  fun mapToDomain(entity: Data): Domain

  fun mapToData(model: Domain): Data
}

open class DataModel
