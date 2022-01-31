package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.domain.model.message.SlackMessage
import javax.inject.Inject

class SlackMessageDataDomMapper @Inject constructor() : EntityMapper<SlackMessage, DBSlackMessage> {
  override fun mapToDomain(entity: DBSlackMessage): SlackMessage {
    return SlackMessage(
      entity.uuid,
      entity.message,
      entity.userId,
      entity.createdBy,
      entity.createdDate,
      entity.modifiedDate
    )
  }

  override fun mapToEntity(model: SlackMessage): DBSlackMessage {
    TODO("Not yet implemented")
  }
}