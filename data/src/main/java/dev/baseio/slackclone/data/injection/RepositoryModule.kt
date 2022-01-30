package dev.baseio.slackclone.data.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.repository.SlackChannelsRepositoryImpl
import dev.baseio.slackclone.domain.repository.ChannelsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun bindLocalChannelsRepository(slackLocalChannelsRepositoryImpl: SlackChannelsRepositoryImpl): ChannelsRepository

}