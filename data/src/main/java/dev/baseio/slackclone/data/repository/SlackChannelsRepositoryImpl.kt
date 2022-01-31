package dev.baseio.slackclone.data.repository

import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SlackChannelsRepositoryImpl @Inject constructor(
  // create local/network source
  private val slackChannelDao: SlackChannelDao,
  private val slackMessageDao: SlackMessageDao,
  private val slackChannelMapper: EntityMapper<SlackChannel, DBSlackChannel>,
) :
  ChannelsRepository {

  init {
    // bad ! change, only for testing purposes
    GlobalScope.launch(Dispatchers.IO) {
      preloadChannels()
      preloadMessages(20)
    }
  }

  private fun preloadMessages(messagesCount: Int) {
    var days = 36
    repeat(messagesCount) {
      slackMessageDao.insert(
        DBSlackMessage(
          UUID.randomUUID().toString(),
          "This is a message and test, this is a message and test, this is a message and test.",
          UUID.randomUUID().toString(),
          "Anmol Verma",
          System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days.toLong())-TimeUnit.HOURS.toMillis(it.toLong()),
          System.currentTimeMillis(),
        )
      )
      days += 10
    }
  }

  private fun preloadChannels() {
    val channels = mutableListOf<DBSlackChannel>()
    repeat(20) {
      channels.add(
        DBSlackChannel(
          UUID.randomUUID().toString(),
          "prj_jp_compose $it",
          "to explore compose...",
          "Anmol Verma",
          Date().toString(),
          Date().toString(),
          false,
          it % 2 == 0, SlackChannelType.GROUP, it % 2 == 0
        )
      )
    }
    slackChannelDao.insertAll(channels)
  }

  override fun fetchChannels(params: SlackChannelType): Flow<List<SlackChannel>> {
    return slackChannelDao.getAllAsFlow()
      .map { list -> list.map { channel -> slackChannelMapper.mapToDomain(channel) } }
  }
}
