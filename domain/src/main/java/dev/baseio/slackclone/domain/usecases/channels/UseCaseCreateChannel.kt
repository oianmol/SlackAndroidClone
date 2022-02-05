package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.channel.DomainLayer
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseCreateChannel(private val channelsRepository: ChannelsRepository) : BaseUseCase<DomainLayer.Channels.SlackChannel, DomainLayer.Channels.SlackChannel> {
  override suspend fun perform(params: DomainLayer.Channels.SlackChannel): DomainLayer.Channels.SlackChannel? {
    return channelsRepository.saveChannel(params)
  }
}