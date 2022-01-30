package dev.baseio.slackclone.uichat.channels

import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseFetchChannels(
        private val channelsRepository: ChannelsRepository,
) :
  BaseUseCase<List<SlackChannel>, SlackChannelType> {
  override fun performStreaming(params: SlackChannelType): Flow<List<SlackChannel>> {
    return channelsRepository.fetchChannels(params)
  }
}
