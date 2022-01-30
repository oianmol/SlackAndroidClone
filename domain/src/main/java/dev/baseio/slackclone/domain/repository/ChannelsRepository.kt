package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {
  fun fetchChannels(params: SlackChannelType): Flow<List<SlackChannel>>

}

