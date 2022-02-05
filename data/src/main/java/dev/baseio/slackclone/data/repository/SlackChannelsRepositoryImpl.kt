package dev.baseio.slackclone.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SlackChannelsRepositoryImpl @Inject constructor(
  private val slackChannelDao: SlackChannelDao,
  private val slackChannelMapper: EntityMapper<DomainLayer.Channels.SlackChannel, DBSlackChannel>,
) :
  ChannelsRepository {

  override fun fetchChannelsPaged(params: String?): Flow<PagingData<DomainLayer.Channels.SlackChannel>> {
    val chatPager = Pager(PagingConfig(pageSize = 20)) {
      params?.takeIf { it.isNotEmpty() }?.let {
        slackChannelDao.channelsByName(params)
      } ?: run {
        slackChannelDao.channelsByName()
      }
    }
    return chatPager.flow.map { messages ->
      messages.map { message ->
        slackChannelMapper.mapToDomain(message)
      }
    }
  }

  override suspend fun channelCount(): Int {
    return withContext(Dispatchers.IO) {
      slackChannelDao.count()
    }
  }

  override fun fetchChannels(): Flow<List<DomainLayer.Channels.SlackChannel>> {
    return slackChannelDao.getAllAsFlow()
      .map { list -> dbToDomList(list) }
  }

  private fun dbToDomList(list: List<DBSlackChannel>) =
    list.map { channel -> slackChannelMapper.mapToDomain(channel) }

  override suspend fun getChannel(uuid: String): DomainLayer.Channels.SlackChannel? {
    val dbSlack = slackChannelDao.getById(uuid)
    return dbSlack?.let { slackChannelMapper.mapToDomain(it) }
  }

  override suspend fun saveChannel(params: DomainLayer.Channels.SlackChannel): DomainLayer.Channels.SlackChannel? {
    return withContext(Dispatchers.IO) {
      slackChannelDao.insert(slackChannelMapper.mapToData(params))
      slackChannelDao.getById(params.uuid!!)?.let { slackChannelMapper.mapToDomain(it) }
    }
  }
}
