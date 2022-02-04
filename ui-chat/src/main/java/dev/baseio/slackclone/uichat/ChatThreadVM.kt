package dev.baseio.slackclone.uichat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.domain.usecases.chat.UseCaseFetchMessages
import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.domain.usecases.chat.UseCaseSendMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatThreadVM @Inject constructor(
  private val useCaseFetchMessages: UseCaseFetchMessages,
  private val useCaseSendMessage: UseCaseSendMessage
) : ViewModel() {
  private var channel: ChatPresentation.SlackChannel? = null
  var chatMessagesFlow = MutableStateFlow<Flow<PagingData<SlackMessage>>?>(null)
  var message = MutableStateFlow("")
  var chatBoxState = MutableStateFlow(BoxState.Collapsed)

  fun requestFetch(slackChannel: ChatPresentation.SlackChannel) {
    this.channel = slackChannel
    chatMessagesFlow.value  = useCaseFetchMessages.performStreaming(slackChannel.uuid)
  }

  fun sendMessage(search: String) {
    if (search.isNotEmpty() && channel?.uuid != null) {
      viewModelScope.launch {
        val message = SlackMessage(
          UUID.randomUUID().toString(),
          channel!!.uuid!!,
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