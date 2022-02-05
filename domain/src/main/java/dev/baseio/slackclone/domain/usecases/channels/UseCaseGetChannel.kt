package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseGetChannel(private val channelsRepository: ChannelsRepository) :
  BaseUseCase<DomainLayerChannels.SlackChannel, String> {
  override suspend fun perform(params: String): DomainLayerChannels.SlackChannel? {
    return channelsRepository.getChannel(uuid = params)
  }
}