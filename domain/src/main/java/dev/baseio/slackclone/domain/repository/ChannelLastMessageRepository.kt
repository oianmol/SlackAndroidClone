package dev.baseio.slackclone.domain.repository

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import kotlinx.coroutines.flow.Flow

interface ChannelLastMessageRepository {
  fun fetchChannels(): Flow<PagingData<DomainLayerMessages.LastMessage>>
}