package dev.baseio.slackclone.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.baseio.slackclone.common.injection.dispatcher.CoroutineDispatcherProvider
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SlackMessagesRepositoryImpl @Inject constructor(
  private val slackMessageDao: SlackMessageDao,
  private val entityMapper: EntityMapper<SlackMessage, DBSlackMessage>,
  private val coroutineMainDispatcherProvider: CoroutineDispatcherProvider
) : MessagesRepository {
  override fun fetchMessages(params: String?): Flow<PagingData<SlackMessage>> {
    return Pager(PagingConfig(pageSize = 20)) {
      slackMessageDao.messagesByDate(params)
    }.flow.mapLatest { it -> it.map { entityMapper.mapToDomain(it) } }
  }

  override suspend fun sendMessage(params: SlackMessage): SlackMessage {
    return withContext(coroutineMainDispatcherProvider.io) {
      slackMessageDao.insert(entityMapper.mapToData(params))
      params
    }
  }
}