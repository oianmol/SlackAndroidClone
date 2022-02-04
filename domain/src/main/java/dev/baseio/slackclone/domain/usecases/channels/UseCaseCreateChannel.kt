package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseCreateChannel(private val channelsRepository: ChannelsRepository) : BaseUseCase<DomSlackChannel, DomSlackChannel> {
  override suspend fun perform(params: DomSlackChannel): DomSlackChannel? {
    return channelsRepository.saveChannel(params)
  }
}