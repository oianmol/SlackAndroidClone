package dev.baseio.slackclone.data.repository

import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import dev.baseio.slackclone.domain.repository.LocalChannelsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class SlackLocalChannelsRepositoryImpl @Inject constructor(
  private val slackChannelDao: SlackChannelDao,
  private val slackChannelMapper: EntityMapper<SlackChannel, DBSlackChannel>,
) :
  LocalChannelsRepository {

  init {
    preloadChannels()
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
