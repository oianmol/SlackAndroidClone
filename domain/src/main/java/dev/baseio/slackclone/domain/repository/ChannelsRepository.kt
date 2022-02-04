package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {
  fun fetchChannels(params: SlackChannelType?): Flow<List<DomSlackChannel>>
  suspend fun saveChannel(params: DomSlackChannel) : DomSlackChannel?
  suspend fun getChannel(uuid: String): DomSlackChannel?
}

