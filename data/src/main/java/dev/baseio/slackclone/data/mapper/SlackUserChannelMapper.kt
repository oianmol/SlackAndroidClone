package dev.baseio.slackclone.data.mapper

import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import javax.inject.Inject

class SlackUserChannelMapper @Inject constructor() :
  EntityMapper<DomainLayerUsers.SlackUser, DBSlackChannel> {
  override fun mapToDomain(entity: DBSlackChannel): DomainLayerUsers.SlackUser {
    TODO("Not yet implemented")
  }

  override fun mapToData(model: DomainLayerUsers.SlackUser): DBSlackChannel {
    return DBSlackChannel(model.login, model.name, avatarUrl = model.picture, isOneToOne = true)
  }
}