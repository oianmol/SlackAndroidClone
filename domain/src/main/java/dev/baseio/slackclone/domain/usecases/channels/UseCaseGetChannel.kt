package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseGetChannel(private val channelsRepository: ChannelsRepository) : BaseUseCase<DomSlackChannel,String> {
  override suspend fun perform(params: String): DomSlackChannel? {
    return channelsRepository.getChannel(uuid = params)
  }
}