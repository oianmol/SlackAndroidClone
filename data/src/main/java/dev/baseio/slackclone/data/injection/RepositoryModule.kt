package dev.baseio.slackclone.data.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.repository.SlackLocalChannelsRepositoryImpl
import dev.baseio.slackclone.domain.repository.LocalChannelsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun bindLocalChannelsRepository(slackLocalChannelsRepositoryImpl: SlackLocalChannelsRepositoryImpl): LocalChannelsRepository

}