package dev.baseio.slackclone.domain.repository

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {
  fun fetchChannels(): Flow<List<DomainLayerChannels.SlackChannel>>
  fun fetchChannelsPaged(params: String?): Flow<PagingData<DomainLayerChannels.SlackChannel>>
  suspend fun saveChannel(params: DomainLayerChannels.SlackChannel): DomainLayerChannels.SlackChannel?
  suspend fun getChannel(uuid: String): DomainLayerChannels.SlackChannel?
  suspend fun channelCount(): Int
  suspend fun saveOneToOneChannels(params: List<DomainLayerUsers.SlackUser>)
}

