package dev.baseio.slackclone.uichat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.uichat.chat.UseCaseFetchMessages
import dev.baseio.slackclone.uichat.models.ChatPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatThreadVM @Inject constructor(
  private val useCaseFetchMessages: UseCaseFetchMessages,
  private val useCaseSendMessage: UseCaseSendMessage
) : ViewModel() {
  var chatMessagesFlow = useCaseFetchMessages.performStreaming(null)
  var message = MutableStateFlow("")
  var chatBoxState = MutableStateFlow(BoxState.Collapsed)

  fun requestFetch(slackChannel: ChatPresentation.SlackChannel) {
    chatMessagesFlow = useCaseFetchMessages.performStreaming(null)
  }


  fun sendMessage(search: String) {
    if (search.isNotEmpty()) {
      viewModelScope.launch {
        val message = SlackMessage(
          UUID.randomUUID().toString(),
          search,
          UUID.randomUUID().toString(),
          "Anmol Verma",
          System.currentTimeMillis(),
          System.currentTimeMillis(),
        )
        useCaseSendMessage.perform(message)
      }
      message.value = ""
      chatBoxState.value = BoxState.Collapsed
    }
  }

}