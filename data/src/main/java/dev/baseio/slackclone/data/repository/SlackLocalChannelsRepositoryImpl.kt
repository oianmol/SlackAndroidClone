package dev.baseio.slackclone.data.repository

import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.domain.model.channel.GroupChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import dev.baseio.slackclone.domain.repository.LocalChannelsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SlackLocalChannelsRepositoryImpl @Inject constructor(private val slackChannelDao: SlackChannelDao) :
  LocalChannelsRepository {
  override fun fetchChannels(params: SlackChannelType): Flow<List<SlackChannel>> {
    return slackChannelDao.getAllAsFlow()
      .map { list -> list.map { channel -> GroupChannel(channel.channelType) } }
  }


}