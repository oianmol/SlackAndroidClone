package dev.baseio.slackclone.chatcore

import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import javax.inject.Inject

class ChannelUIModelMapper @Inject constructor() :
  UiModelMapper<DomSlackChannel, ChatPresentation.SlackChannel> {
  override fun mapToPresentation(model: DomSlackChannel): ChatPresentation.SlackChannel {
    return ChatPresentation.SlackChannel(
      model.name,
      model.isPrivate,
      model.uuid,
      model.createdDate,
      model.modifiedDate,
      model.isMuted
    )
  }

  override fun mapToDomain(modelItem: ChatPresentation.SlackChannel): DomSlackChannel {
    TODO("Not yet implemented")
  }
}