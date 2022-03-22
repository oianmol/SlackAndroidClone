package dev.baseio.slackclone.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import dev.baseio.slackclone.domain.repository.ChannelLastMessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SlackChannelLastMessageRepository @Inject constructor(
  private val slackChannelDao: SlackChannelDao,
  private val messagesMapper: EntityMapper<DomainLayerMessages.SlackMessage, DBSlackMessage>,
  private val slackChannelMapper: EntityMapper<DomainLayerChannels.SlackChannel, DBSlackChannel>
) : ChannelLastMessageRepository {
  override fun fetchChannels(): Flow<PagingData<DomainLayerMessages.LastMessage>> {
    val chatPager = Pager(PagingConfig(pageSize = 20)) {
      slackChannelDao.getChannelsWithLastMessage()
    }
    return chatPager.flow.map {
      it.map {
        DomainLayerMessages.LastMessage(
          slackChannelMapper.mapToDomain(it.dbSlackChannel),
          messagesMapper.mapToDomain(it.message)
        )
      }
    }
  }
}