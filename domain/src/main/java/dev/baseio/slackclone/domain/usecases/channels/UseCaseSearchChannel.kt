package dev.baseio.slackclone.domain.usecases.channels

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseSearchChannel(private val channelsRepository: ChannelsRepository) :
  BaseUseCase<PagingData<DomainLayerChannels.SlackChannel>, String> {
  override fun performStreaming(params: String?): Flow<PagingData<DomainLayerChannels.SlackChannel>> {
    return channelsRepository.fetchChannelsPaged(params)
  }


}
