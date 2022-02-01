package dev.baseio.slackclone.data.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.repository.SlackChannelsRepositoryImpl
import dev.baseio.slackclone.data.repository.SlackMessagesRepositoryImpl
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.domain.repository.MessagesRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  @Singleton
  abstract fun bindLocalChannelsRepository(slackLocalChannelsRepositoryImpl: SlackChannelsRepositoryImpl): ChannelsRepository

  @Binds
  @Singleton
  abstract fun bindMessagesRepository(slackMessagesRepositoryImpl: SlackMessagesRepositoryImpl): MessagesRepository
}