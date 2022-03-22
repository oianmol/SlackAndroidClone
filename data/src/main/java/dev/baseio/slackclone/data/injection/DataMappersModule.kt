package dev.baseio.slackclone.data.injection

import com.github.vatbub.randomusers.result.RandomUser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.data.mapper.*
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataMappersModule {

  @Binds
  @Singleton
  abstract fun bindSlackUserChannelMapper(slackUserChannelMapper: SlackUserChannelMapper): EntityMapper<DomainLayerUsers.SlackUser, DBSlackChannel>

  @Binds
  @Singleton
  abstract fun bindSlackUserDataDomainMapper(slackUserMapper: SlackUserMapper): EntityMapper<DomainLayerUsers.SlackUser, RandomUser>

  @Binds
  @Singleton
  abstract fun bindSlackChannelDataDomainMapper(slackChannelMapper: SlackChannelMapper): EntityMapper<DomainLayerChannels.SlackChannel, DBSlackChannel>

  @Binds
  @Singleton
  abstract fun bindSlackMessageDataDomMapper(slackMessageMapper: SlackMessageMapper): EntityMapper<DomainLayerMessages.SlackMessage, DBSlackMessage>
}