package dev.baseio.slackclone.domain.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.repository.MessagesRepository
import dev.baseio.slackclone.uichat.UseCaseSendMessage
import dev.baseio.slackclone.uichat.channels.UseCaseFetchChannels
import dev.baseio.slackclone.uichat.chat.UseCaseFetchMessages

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchChannels(channelsRepository: ChannelsRepository) =
    UseCaseFetchChannels(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchMessages(messagesRepository: MessagesRepository) =
    UseCaseFetchMessages(messagesRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseSendMessage(messagesRepository: MessagesRepository) =
    UseCaseSendMessage(messagesRepository)
}