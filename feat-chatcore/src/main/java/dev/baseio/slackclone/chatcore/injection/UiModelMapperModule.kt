package dev.baseio.slackclone.chatcore.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.chatcore.ChannelUIModelMapper
import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModelMapperModule {

  @Binds
  @Singleton
  @ChatUiModelMapper
  abstract fun providesUiModelMapper(channelUIModelMapper: ChannelUIModelMapper): UiModelMapper<DomainLayer.Channels.SlackChannel, ChatPresentation.SlackChannel>
}
