package dev.baseio.slackclone.domain.repository

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
  fun fetchMessages(params: String?): Flow<PagingData<DomainLayerMessages.SlackMessage>>
  suspend fun sendMessage(params: DomainLayerMessages.SlackMessage): DomainLayerMessages.SlackMessage
}