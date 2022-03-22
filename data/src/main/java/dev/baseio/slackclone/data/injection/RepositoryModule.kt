package dev.baseio.slackclone.data.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.repository.SlackChannelLastMessageRepository
import dev.baseio.slackclone.data.repository.SlackChannelsRepositoryImpl
import dev.baseio.slackclone.data.repository.SlackMessagesRepositoryImpl
import dev.baseio.slackclone.data.repository.SlackUserRepository
import dev.baseio.slackclone.domain.repository.ChannelLastMessageRepository
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.repository.MessagesRepository
import dev.baseio.slackclone.domain.repository.UsersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  @Singleton
  abstract fun bindLocalChannelsRepository(slackLocalChannelsRepositoryImpl: SlackChannelsRepositoryImpl): ChannelsRepository

  @Binds
  @Singleton
  abstract fun bindSlackUserRepository(slackUserRepository: SlackUserRepository): UsersRepository

  @Binds
  @Singleton
  abstract fun bindMessagesRepository(slackMessagesRepositoryImpl: SlackMessagesRepositoryImpl): MessagesRepository

  @Binds
  @Singleton
  abstract fun bindChannelLastMessageRepository(slackChannelLastMessageRepository: SlackChannelLastMessageRepository): ChannelLastMessageRepository
}