package dev.baseio.slackclone.uichat

import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.domain.repository.MessagesRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseSendMessage(private val messagesRepository: MessagesRepository) :BaseUseCase<Unit,SlackMessage>{
  override suspend fun perform(params: SlackMessage) {
    return messagesRepository.sendMessage(params)
  }
}
