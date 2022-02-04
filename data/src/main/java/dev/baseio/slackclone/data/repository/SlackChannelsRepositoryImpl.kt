package dev.baseio.slackclone.data.repository

import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class SlackChannelsRepositoryImpl @Inject constructor(
  private val slackChannelDao: SlackChannelDao,
  private val slackChannelMapper: EntityMapper<DomSlackChannel, DBSlackChannel>,
) :
  ChannelsRepository {

  override fun fetchChannels(params: SlackChannelType?): Flow<List<DomSlackChannel>> {
    return slackChannelDao.getAllAsFlow()
      .map { list -> list.map { channel -> slackChannelMapper.mapToDomain(channel) } }
  }

  override suspend fun getChannel(uuid: String): DomSlackChannel? {
    val dbSlack = slackChannelDao.getById(uuid)
    return dbSlack?.let { slackChannelMapper.mapToDomain(it) }
  }

  override suspend fun saveChannel(params: DomSlackChannel): DomSlackChannel? {
    return withContext(Dispatchers.IO) {
      slackChannelDao.insert(slackChannelMapper.mapToData(params))
      slackChannelDao.getById(params.uuid!!)?.let { slackChannelMapper.mapToDomain(it) }
    }
  }
}
