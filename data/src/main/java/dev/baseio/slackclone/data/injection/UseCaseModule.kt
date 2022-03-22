package dev.baseio.slackclone.data.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.baseio.slackclone.domain.repository.ChannelLastMessageRepository
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.repository.MessagesRepository
import dev.baseio.slackclone.domain.repository.UsersRepository
import dev.baseio.slackclone.domain.usecases.channels.*
import dev.baseio.slackclone.domain.usecases.chat.UseCaseSendMessage
import dev.baseio.slackclone.domain.usecases.chat.UseCaseFetchMessages

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchChannels(channelsRepository: ChannelsRepository) =
    UseCaseFetchChannels(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchChannelWithLastMessage(channelsRepository: ChannelLastMessageRepository) =
    UseCaseFetchChannelsWithLastMessage(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchMessages(messagesRepository: MessagesRepository) =
    UseCaseFetchMessages(messagesRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseSendMessage(messagesRepository: MessagesRepository) =
    UseCaseSendMessage(messagesRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseCreateChannel(channelsRepository: ChannelsRepository) =
    UseCaseCreateChannel(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseCreateChannels(channelsRepository: ChannelsRepository) =
    UseCaseCreateChannels(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseGetChannel(channelsRepository: ChannelsRepository) =
    UseCaseGetChannel(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchChannelCount(channelsRepository: ChannelsRepository) =
    UseCaseFetchChannelCount(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseSearchChannel(channelsRepository: ChannelsRepository) =
    UseCaseSearchChannel(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchUsers(slackUsersRepository: UsersRepository) =
    UseCaseFetchUsers(slackUsersRepository)
}