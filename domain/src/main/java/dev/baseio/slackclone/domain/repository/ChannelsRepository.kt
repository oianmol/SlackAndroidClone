package dev.baseio.slackclone.domain.repository

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {
  fun fetchChannels(params: SlackChannelType?): Flow<List<DomSlackChannel>>
  fun fetchChannelsPaged(params: String?): Flow<PagingData<DomSlackChannel>>
  suspend fun saveChannel(params: DomSlackChannel) : DomSlackChannel?
  suspend fun getChannel(uuid: String): DomSlackChannel?
}

