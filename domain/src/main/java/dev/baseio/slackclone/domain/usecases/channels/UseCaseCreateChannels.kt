package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseCreateChannels(private val channelsRepository: ChannelsRepository) :
  BaseUseCase<Unit, List<DomainLayerUsers.SlackUser>> {
  override suspend fun perform(params: List<DomainLayerUsers.SlackUser>) {
    return channelsRepository.saveOneToOneChannels(params)
  }
}