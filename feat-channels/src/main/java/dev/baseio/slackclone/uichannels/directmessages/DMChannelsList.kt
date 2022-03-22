package dev.baseio.slackclone.uichannels.directmessages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.chatcore.views.DMLastMessageItem

@Composable
fun DMChannelsList(
  onItemClick: (UiLayerChannels.SlackChannel) -> Unit,
  channelVM: DMessageViewModel = hiltViewModel()
) {

  val channels by channelVM.channels.collectAsState()
  val channelsFlow = channels.collectAsLazyPagingItems()
  val listState = rememberLazyListState()

  LaunchedEffect(key1 = Unit) {
    channelVM.refresh()
  }

  LazyColumn(state = listState) {
    for (channelIndex in 0 until channelsFlow.itemCount) {
      val channel = channelsFlow.peek(channelIndex)!!

      item {
        DMLastMessageItem({
          onItemClick(it)
        }, channelVM.mapToUI(channel.channel), channel.message)
      }
    }
  }
}