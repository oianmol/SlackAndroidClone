package dev.baseio.slackclone.chatcore.injection

import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import javax.inject.Inject

class UserChannelUiMapper @Inject constructor():
  UiModelMapper<DomainLayerUsers.SlackUser, UiLayerChannels.SlackChannel> {
  override fun mapToPresentation(model: DomainLayerUsers.SlackUser): UiLayerChannels.SlackChannel {
    TODO("Not yet implemented")
  }

  override fun mapToDomain(modelItem: UiLayerChannels.SlackChannel): DomainLayerUsers.SlackUser {
    TODO("Not yet implemented")
  }
}