package dev.baseio.slackclone.uichat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.uichat.chat.UseCaseFetchMessages
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ChatThreadVM @Inject constructor(
  useCaseFetchMessages: UseCaseFetchMessages,
  private val useCaseSendMessage: UseCaseSendMessage
) : ViewModel() {
  val chatMessagesFlow = useCaseFetchMessages.performStreaming(null)

  fun sendMessage(search: String) {
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
  }

}