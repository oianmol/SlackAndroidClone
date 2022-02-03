package dev.baseio.slackclone.uichat.chatscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.uichat.ChatThreadVM
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatMessagesUI(viewModel: ChatThreadVM, modifier: Modifier) {
  val messages = viewModel.chatMessagesFlow.collectAsLazyPagingItems()
  val listState = rememberLazyListState()

  LazyColumn(state = listState, reverseLayout = true, modifier = modifier) {
    var lastDrawnMessage: String? = null
    for (messageIndex in 0 until messages.itemCount) {
      val message = messages.peek(messageIndex)!!
      item {
        ChatMessage(message)
      }
      lastDrawnMessage = message.createdDate.calendar().formattedMonthDate()
      if (!isLastMessage(messageIndex, messages)) {
        val nextMessageMonth =
          messages.peek(messageIndex + 1)?.createdDate?.calendar()?.formattedMonthDate()
        if (nextMessageMonth != lastDrawnMessage) {
          item {
            ChatHeader(message.createdDate)
          }
        }
      } else {
        item {
          ChatHeader(message.createdDate)
        }
      }

    }
  }
}

private fun isLastMessage(
  messageIndex: Int,
  messages: LazyPagingItems<SlackMessage>
) = messageIndex == messages.itemCount.minus(1)

@Composable
private fun ChatHeader(createdDate: Long) {
  Column(Modifier.padding(start = 8.dp, end = 8.dp)) {
    Text(
      createdDate.calendar().formattedMonthDate(),
      style = SlackCloneTypography.subtitle2.copy(
        fontWeight = FontWeight.Bold,
        color = SlackCloneColorProvider.colors.textPrimary
      ), modifier = Modifier.padding(4.dp)
    )
    Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
  }
}
