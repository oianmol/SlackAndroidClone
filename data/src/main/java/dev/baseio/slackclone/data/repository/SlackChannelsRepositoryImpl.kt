package dev.baseio.slackclone.data.repository

import androidx.paging.*
import dev.baseio.slackclone.common.injection.dispatcher.CoroutineDispatcherProvider
import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import dev.baseio.slackclone.domain.repository.ChannelsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SlackChannelsRepositoryImpl @Inject constructor(
  private val slackChannelDao: SlackChannelDao,
  private val slackUserChannelMapper: EntityMapper<DomainLayerUsers.SlackUser, DBSlackChannel>,
  private val slackChannelMapper: EntityMapper<DomainLayerChannels.SlackChannel, DBSlackChannel>,
  private val coroutineMainDispatcherProvider: CoroutineDispatcherProvider
) :
  ChannelsRepository {

  override fun fetchChannelsPaged(params: String?): Flow<PagingData<DomainLayerChannels.SlackChannel>> {
    val chatPager = Pager(PagingConfig(pageSize = 20)) {
      params?.takeIf { it.isNotEmpty() }?.let {
        slackChannelDao.channelsByName(params)
      } ?: run {
        slackChannelDao.channelsByName()
      }
    }
   return chatPager.flow.map {
     it.map {message->
       slackChannelMapper.mapToDomain(message)
     }
   }
  }

  override suspend fun channelCount(): Int {
    return withContext(coroutineMainDispatcherProvider.io) {
      slackChannelDao.count()
    }
  }

  override fun fetchChannels(): Flow<List<DomainLayerChannels.SlackChannel>> {
    return slackChannelDao.getAllAsFlow()
      .map { list -> dbToDomList(list) }
  }

  private fun dbToDomList(list: List<DBSlackChannel>) =
    list.map { channel -> slackChannelMapper.mapToDomain(channel) }

  override suspend fun getChannel(uuid: String): DomainLayerChannels.SlackChannel? {
    val dbSlack = slackChannelDao.getById(uuid)
    return dbSlack?.let { slackChannelMapper.mapToDomain(it) }
  }

  override suspend fun saveOneToOneChannels(params: List<DomainLayerUsers.SlackUser>) {
    return withContext(coroutineMainDispatcherProvider.io) {
      slackChannelDao.insertAll(params.map {
        slackUserChannelMapper.mapToData(it)
      })
    }
  }

  override suspend fun saveChannel(params: DomainLayerChannels.SlackChannel): DomainLayerChannels.SlackChannel? {
    return withContext(coroutineMainDispatcherProvider.io) {
      slackChannelDao.insert(slackChannelMapper.mapToData(params))
      slackChannelDao.getById(params.uuid!!)?.let { slackChannelMapper.mapToDomain(it) }
    }
  }
}
