package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.domain.model.message.SlackMessage
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SlackMessageMapper @Inject constructor() : EntityMapper<SlackMessage, DBSlackMessage> {
  override fun mapToDomain(entity: DBSlackMessage): SlackMessage {
    return SlackMessage(
      entity.uuid,
      entity.channelId,
      entity.message,
      entity.userId,
      entity.createdBy,
      entity.createdDate,
      entity.modifiedDate
    )
  }

  override fun mapToData(model: SlackMessage): DBSlackMessage {
    return DBSlackMessage(
      model.uuid,
      model.channelId,
      model.message,
      model.userId,
      model.createdBy,
      model.createdDate,
      model.modifiedDate,
    )
  }
}