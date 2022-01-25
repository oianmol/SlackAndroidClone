package dev.baseio.slackclone.domain.mappers

open class DomainModel

open class UIModel

interface UiModelMapper<M : DomainModel, MI : UIModel> {
  fun mapToPresentation(model: M): UIModel

  fun mapToDomain(modelItem: MI): DomainModel
}