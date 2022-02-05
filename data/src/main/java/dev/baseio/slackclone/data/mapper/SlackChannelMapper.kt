package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import javax.inject.Inject

class SlackChannelMapper @Inject constructor() :
  EntityMapper<DomainLayerChannels.SlackChannel, DBSlackChannel> {
  override fun mapToDomain(entity: DBSlackChannel): DomainLayerChannels.SlackChannel {
    return DomainLayerChannels.SlackChannel(
      isStarred = entity.isStarred,
      isPrivate = entity.isPrivate,
      uuid = entity.uuid,
      name = entity.name,
      isMuted = entity.isMuted,
      createdDate = entity.createdDate,
      modifiedDate = entity.modifiedDate,
      isShareOutSide = entity.isShareOutSide,
      isOneToOne = entity.isOneToOne,
      avatarUrl = entity.avatarUrl
    )
  }

  override fun mapToData(model: DomainLayerChannels.SlackChannel): DBSlackChannel {
    return DBSlackChannel(
      model.uuid ?: model.name!!,
      model.name,
      isStarred = model.isStarred,
      createdDate = model.createdDate,
      modifiedDate = model.modifiedDate,
      isPrivate = model.isPrivate,
      isShareOutSide = model.isShareOutSide,
      isOneToOne = model.isOneToOne, avatarUrl = model.avatarUrl
    )
  }
}