package dev.baseio.slackclone.domain.usecases.chat

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import dev.baseio.slackclone.domain.repository.MessagesRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseFetchMessages(private val messagesRepository: MessagesRepository) :
  BaseUseCase<PagingData<DomainLayerMessages.SlackMessage>, String> {
  override fun performStreaming(params: String?): Flow<PagingData<DomainLayerMessages.SlackMessage>> {
    return messagesRepository.fetchMessages(params)
  }
}
