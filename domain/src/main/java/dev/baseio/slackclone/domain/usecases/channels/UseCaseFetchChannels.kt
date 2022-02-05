package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.channel.DomainLayer
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseFetchChannels(
  private val channelsRepository: ChannelsRepository,
) : BaseUseCase<List<DomainLayer.Channels.SlackChannel>, Unit> {

  override fun performStreaming(params: Unit?): Flow<List<DomainLayer.Channels.SlackChannel>> {
    return channelsRepository.fetchChannels()
  }

}
