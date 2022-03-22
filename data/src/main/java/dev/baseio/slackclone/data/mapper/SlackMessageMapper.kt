package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SlackMessageMapper @Inject constructor() : EntityMapper<DomainLayerMessages.SlackMessage, DBSlackMessage> {
  override fun mapToDomain(entity: DBSlackMessage): DomainLayerMessages.SlackMessage {
    return DomainLayerMessages.SlackMessage(
      entity.uuid,
      entity.channelId,
      entity.message,
      entity.userId,
      entity.createdBy,
      entity.createdDate,
      entity.modifiedDate
    )
  }

  override fun mapToData(model: DomainLayerMessages.SlackMessage): DBSlackMessage {
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