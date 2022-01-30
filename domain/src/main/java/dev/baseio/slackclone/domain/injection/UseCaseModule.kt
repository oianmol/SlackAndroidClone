package dev.baseio.slackclone.domain.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import dev.baseio.slackclone.uichat.channels.UseCaseFetchChannels

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchChannels(channelsRepository: ChannelsRepository) =
    UseCaseFetchChannels(channelsRepository)
}