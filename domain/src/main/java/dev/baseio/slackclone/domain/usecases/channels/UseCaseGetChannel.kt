package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.channel.DomainLayer
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseGetChannel(private val channelsRepository: ChannelsRepository) :
  BaseUseCase<DomainLayer.Channels.SlackChannel, String> {
  override suspend fun perform(params: String): DomainLayer.Channels.SlackChannel? {
    return channelsRepository.getChannel(uuid = params)
  }
}