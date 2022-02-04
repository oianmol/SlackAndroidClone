package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseFetchChannels(
        private val channelsRepository: ChannelsRepository,
) :
  BaseUseCase<List<DomSlackChannel>, SlackChannelType> {
  override fun performStreaming(params: SlackChannelType?): Flow<List<DomSlackChannel>> {
    return channelsRepository.fetchChannels(params)
  }
}
