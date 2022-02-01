package dev.baseio.slackclone.uichat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.uichat.chat.UseCaseFetchMessages
import kotlinx.coroutines.flow.MutableStateFlow
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
  var message = MutableStateFlow("")
  var isExpanded = MutableStateFlow(false)

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
    }
  }

}