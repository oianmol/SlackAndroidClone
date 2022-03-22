package dev.baseio.slackclone.domain.usecases.chat

import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import dev.baseio.slackclone.domain.repository.MessagesRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseSendMessage(private val messagesRepository: MessagesRepository) :BaseUseCase<DomainLayerMessages.SlackMessage, DomainLayerMessages.SlackMessage>{
  override suspend fun perform(params: DomainLayerMessages.SlackMessage): DomainLayerMessages.SlackMessage {
    return messagesRepository.sendMessage(params)
  }
}
