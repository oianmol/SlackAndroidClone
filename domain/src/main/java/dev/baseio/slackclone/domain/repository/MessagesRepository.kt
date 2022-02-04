package dev.baseio.slackclone.domain.repository

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.model.message.SlackMessage
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
  fun fetchMessages(params: String?): Flow<PagingData<SlackMessage>>
  suspend fun sendMessage(params: SlackMessage) : SlackMessage
}