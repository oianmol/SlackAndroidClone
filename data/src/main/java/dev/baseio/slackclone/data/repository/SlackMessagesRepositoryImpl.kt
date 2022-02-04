package dev.baseio.slackclone.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.baseio.slackclone.data.injection.RepositoryCoroutineContext
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SlackMessagesRepositoryImpl @Inject constructor(
  private val slackMessageDao: SlackMessageDao,
  private val entityMapper: EntityMapper<SlackMessage, DBSlackMessage>,
  @RepositoryCoroutineContext private val coroutineContext: CoroutineContext
) :
  MessagesRepository {
  private val chatPager = Pager(PagingConfig(pageSize = 20)) {
    slackMessageDao.messagesByDate()
  }

  override fun fetchMessages(params: DomSlackChannel?): Flow<PagingData<SlackMessage>> {
    return chatPager.flow.map { messages ->
      messages.map { message ->
        entityMapper.mapToDomain(message)
      }
    }
  }

  override suspend fun sendMessage(params: SlackMessage): SlackMessage {
    return withContext(coroutineContext) {
      val dbMessage = slackMessageDao.insert(entityMapper.mapToData(params))
      entityMapper.mapToDomain(slackMessageDao.getById(params.uuid))
    }
  }
}