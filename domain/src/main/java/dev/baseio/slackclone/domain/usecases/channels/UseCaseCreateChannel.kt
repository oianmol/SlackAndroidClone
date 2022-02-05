package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseCreateChannel(private val channelsRepository: ChannelsRepository) : BaseUseCase<DomainLayerChannels.SlackChannel, DomainLayerChannels.SlackChannel> {
  override suspend fun perform(params: DomainLayerChannels.SlackChannel): DomainLayerChannels.SlackChannel? {
    return channelsRepository.saveChannel(params)
  }
}