package dev.baseio.slackclone.data.injection

import com.github.vatbub.randomusers.result.RandomUser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.data.mapper.SlackChannelDataDomainMapper
import dev.baseio.slackclone.data.mapper.SlackMessageDataDomMapper
import dev.baseio.slackclone.data.mapper.SlackUserDataDomainMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.domain.model.users.DomRandomUser
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataMappersModule {

  @Binds
  @Singleton
  abstract fun bindSlackUserDataDomainMapper(slackUserDataDomainMapper: SlackUserDataDomainMapper): EntityMapper<DomRandomUser, RandomUser>

  @Binds
  @Singleton
  abstract fun provideSlackChannelDataDomainMapper(slackChannelDataDomainMapper: SlackChannelDataDomainMapper): EntityMapper<DomainLayer.Channels.SlackChannel, DBSlackChannel>

  @Binds
  @Singleton
  abstract fun provideSlackMessageDataDomMapper(slackMessageDataDomMapper: SlackMessageDataDomMapper): EntityMapper<SlackMessage, DBSlackMessage>
}