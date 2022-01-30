package dev.baseio.slackclone.data.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.data.mapper.SlackChannelDataDomainMapper
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataMappersModule {

  @Binds
  @Singleton
  abstract fun provideSlackChannelDataDomainMapper(slackChannelDataDomainMapper: SlackChannelDataDomainMapper) : EntityMapper<SlackChannel, DBSlackChannel>
}