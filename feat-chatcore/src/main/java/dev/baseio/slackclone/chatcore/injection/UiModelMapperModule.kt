package dev.baseio.slackclone.chatcore.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.chatcore.ChannelUIModelMapper
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModelMapperModule {

  @Binds
  @Singleton
  abstract fun bindSlackUserChannelMapper(userChannelUiMapper: UserChannelUiMapper): UiModelMapper<DomainLayerUsers.SlackUser, UiLayerChannels.SlackChannel>

  @Binds
  @Singleton
  abstract fun bindChannelUIModelMapper(channelUIModelMapper: ChannelUIModelMapper): UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
}
