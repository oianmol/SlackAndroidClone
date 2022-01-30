package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackOneToOneChannel
import dev.baseio.slackclone.domain.model.channel.SlackGroupChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import javax.inject.Inject

class SlackChannelDataDomainMapper @Inject constructor(): EntityMapper<SlackChannel, DBSlackChannel> {
  override fun mapToDomain(entity: DBSlackChannel): SlackChannel {
    if (entity.channelType == SlackChannelType.GROUP) {
      return SlackGroupChannel(
        description = entity.description,
        createdBy = entity.createdBy,
        isStarred = entity.isStarred,
        isPrivate = entity.isPrivate,
        uuid = entity.uuid,
        name = entity.name,
        createdDate = entity.createdDate,
        modifiedDate = entity.modifiedDate,
        isMuted = entity.isMuted
      )
    } else {
      return SlackOneToOneChannel(
        uuid = entity.uuid,
        name = entity.name,
        createdDate = entity.createdDate,
        modifiedDate = entity.modifiedDate,
        isMuted = entity.isMuted,
        isPrivate = entity.isPrivate
      )
    }
  }

  override fun mapToEntity(model: SlackChannel): DBSlackChannel {
    TODO("We don't need this yet")
  }
}