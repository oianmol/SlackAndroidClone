package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseFetchChannelCount(private val channelsRepository: ChannelsRepository) : BaseUseCase<Int,Unit> {

  override suspend fun perform(): Int {
    return channelsRepository.channelCount()
  }
}