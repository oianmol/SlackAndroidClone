package dev.baseio.slackclone.uichat

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import dev.baseio.slackclone.uichat.chat.UseCaseFetchMessages
import javax.inject.Inject

@HiltViewModel
class ChatThreadVM @Inject constructor(useCaseFetchMessages: UseCaseFetchMessages) : ViewModel() {
  val chatMessagesFlow = useCaseFetchMessages.performStreaming(null)

  fun sendMessage() {

  }

}