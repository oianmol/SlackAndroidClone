package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import javax.inject.Inject

class SlackChannelDataDomainMapper @Inject constructor() :
  EntityMapper<DomainLayer.Channels.SlackChannel, DBSlackChannel> {
  override fun mapToDomain(entity: DBSlackChannel): DomainLayer.Channels.SlackChannel {
    return DomainLayer.Channels.SlackChannel(
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

  override fun mapToData(model: DomainLayer.Channels.SlackChannel): DBSlackChannel {
    return DBSlackChannel(
      model.uuid ?: model.name!!,
      model.name,
      isStarred = model.isStarred,
      createdDate = model.createdDate,
      modifiedDate = model.modifiedDate,
      isPrivate = model.isPrivate,
      isShareOutSide = model.isShareOutSide,
    )
  }
}