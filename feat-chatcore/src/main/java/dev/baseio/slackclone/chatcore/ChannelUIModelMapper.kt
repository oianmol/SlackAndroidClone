package dev.baseio.slackclone.chatcore

import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import javax.inject.Inject

class ChannelUIModelMapper @Inject constructor() :
  UiModelMapper<DomainLayer.Channels.SlackChannel, ChatPresentation.SlackChannel> {
  override fun mapToPresentation(model: DomainLayer.Channels.SlackChannel): ChatPresentation.SlackChannel {
    return ChatPresentation.SlackChannel(
      model.name,
      model.isPrivate,
      model.uuid,
      model.createdDate,
      model.modifiedDate,
      model.isMuted
    )
  }

  override fun mapToDomain(modelItem: ChatPresentation.SlackChannel): DomainLayer.Channels.SlackChannel {
    TODO("Not yet implemented")
  }
}