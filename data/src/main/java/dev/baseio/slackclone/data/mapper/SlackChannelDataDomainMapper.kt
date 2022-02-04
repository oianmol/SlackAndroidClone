package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import javax.inject.Inject

class SlackChannelDataDomainMapper @Inject constructor() :
  EntityMapper<DomSlackChannel, DBSlackChannel> {
  override fun mapToDomain(entity: DBSlackChannel): DomSlackChannel {
    return DomSlackChannel(
      channel = entity.channelType,
      isStarred = entity.isStarred,
      isPrivate = entity.isPrivate,
      uuid = entity.uuid,
      name = entity.name,
      isMuted = entity.isMuted,
      createdDate = entity.createdDate,
      modifiedDate = entity.modifiedDate,
      isShareOutSide = entity.isShareOutSide
    )
  }

  override fun mapToData(model: DomSlackChannel): DBSlackChannel {
    return DBSlackChannel(
      model.uuid ?: model.name!!,
      model.name,
      isStarred = model.isStarred,
      createdDate = model.createdDate,
      modifiedDate = model.modifiedDate,
      isPrivate = model.isPrivate,
      channelType = model.channel,
      isShareOutSide = model.isShareOutSide,
    )
  }
}