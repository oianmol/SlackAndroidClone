package dev.baseio.slackclone.uichat.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.uichat.models.ChannelUIModelMapper
import dev.baseio.slackclone.uichat.models.ChatPresentation
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModelMapperModule {

  @Binds
  @Singleton
  @ChatUiModelMapper
  abstract fun providesUiModelMapper(channelUIModelMapper: ChannelUIModelMapper): UiModelMapper<SlackChannel, ChatPresentation.SlackChannel>
}

@Qualifier
annotation class ChatUiModelMapper