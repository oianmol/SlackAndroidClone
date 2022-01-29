package dev.baseio.slackclone.domain.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.domain.repository.LocalChannelsRepository
import dev.baseio.slackclone.uichat.channels.UseCaseFetchChannels
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchChannels(localChannelsRepository: LocalChannelsRepository) =
    UseCaseFetchChannels(localChannelsRepository)
}