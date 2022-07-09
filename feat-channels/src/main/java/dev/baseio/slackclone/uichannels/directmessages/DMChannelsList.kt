package dev.baseio.slackclone.uichannels.directmessages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.paging.compose.collectAsLazyPagingItems
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.chatcore.views.DMLastMessageItem
import androidx.lifecycle.compose.collectAsStateWithLifecycle
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DMChannelsList(
  onItemClick: (UiLayerChannels.SlackChannel) -> Unit,
  channelVM: DMessageViewModel = hiltViewModel()
) {

  val channels by channelVM.channels.collectAsStateWithLifecycle()
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