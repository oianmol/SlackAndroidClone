package dev.baseio.slackclone.domain.repository

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.message.SlackMessage
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
  fun fetchMessages(params: SlackChannel?): Flow<PagingData<SlackMessage>>
  suspend fun sendMessage(params: SlackMessage)
}