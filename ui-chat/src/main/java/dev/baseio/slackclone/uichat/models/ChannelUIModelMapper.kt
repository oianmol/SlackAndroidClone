package dev.baseio.slackclone.uichat.models

import dev.baseio.slackclone.domain.mappers.DomainModel
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import javax.inject.Inject

class ChannelUIModelMapper @Inject constructor() :
  UiModelMapper<SlackChannel, ChatPresentation.SlackChannel> {
  override fun mapToPresentation(model: SlackChannel): ChatPresentation.SlackChannel {
    return ChatPresentation.SlackChannel(
      model.name,
      model.isPrivate,
      model.uuid,
      model.createdDate,
      model.modifiedDate,
      model.isMuted
    )
  }

  override fun mapToDomain(modelItem: ChatPresentation.SlackChannel): SlackChannel {
    TODO("Not yet implemented")
  }
}