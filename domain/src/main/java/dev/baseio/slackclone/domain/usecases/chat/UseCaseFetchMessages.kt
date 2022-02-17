package dev.baseio.slackclone.domain.usecases.chat

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.domain.repository.MessagesRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseFetchMessages(private val messagesRepository: MessagesRepository) :
  BaseUseCase<PagingData<SlackMessage>, String> {
  override fun performStreaming(params: String?): Flow<PagingData<SlackMessage>> {
    return messagesRepository.fetchMessages(params)
  }
}
