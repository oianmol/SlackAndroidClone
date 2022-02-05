package dev.baseio.slackclone.chatcore

import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import javax.inject.Inject

class ChannelUIModelMapper @Inject constructor() :
  UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel> {
  override fun mapToPresentation(model: DomainLayerChannels.SlackChannel): UiLayerChannels.SlackChannel {
    return UiLayerChannels.SlackChannel(
      model.name,
      model.isPrivate,
      model.uuid,
      model.createdDate,
      model.modifiedDate,
      model.isMuted,
      model.isOneToOne,
      model.avatarUrl
    )
  }

  override fun mapToDomain(modelItem: UiLayerChannels.SlackChannel): DomainLayerChannels.SlackChannel {
    TODO("Not yet implemented")
  }
}