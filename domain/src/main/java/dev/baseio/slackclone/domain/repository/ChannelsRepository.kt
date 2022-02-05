package dev.baseio.slackclone.domain.repository

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {
  fun fetchChannels(): Flow<List<DomainLayer.Channels.SlackChannel>>
  fun fetchChannelsPaged(params: String?): Flow<PagingData<DomainLayer.Channels.SlackChannel>>
  suspend fun saveChannel(params: DomainLayer.Channels.SlackChannel) : DomainLayer.Channels.SlackChannel?
  suspend fun getChannel(uuid: String): DomainLayer.Channels.SlackChannel?
  suspend fun channelCount(): Int
}

