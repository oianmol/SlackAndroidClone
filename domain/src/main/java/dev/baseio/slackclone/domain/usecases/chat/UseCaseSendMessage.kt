package dev.baseio.slackclone.domain.usecases.chat

import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.domain.repository.MessagesRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseSendMessage(private val messagesRepository: MessagesRepository) :BaseUseCase<SlackMessage,SlackMessage>{
  override suspend fun perform(params: SlackMessage): SlackMessage {
    return messagesRepository.sendMessage(params)
  }
}
