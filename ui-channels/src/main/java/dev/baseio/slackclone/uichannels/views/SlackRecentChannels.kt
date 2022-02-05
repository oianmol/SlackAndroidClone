package dev.baseio.slackclone.uichannels.views

import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.baseio.slackclone.uichannels.SlackChannelVM
import dev.baseio.slackclone.chatcore.data.ExpandCollapseModel
import dev.baseio.slackclone.uichannels.R
import androidx.compose.runtime.*
import dev.baseio.slackclone.chatcore.data.UiLayer

@Composable
fun SlackRecentChannels(
  onItemClick: (UiLayer.Channels.SlackChannel) -> Unit = {},
  channelVM: SlackChannelVM = hiltViewModel(),
  onClickAdd: () -> Unit
) {
  val recent = stringResource(R.string.Recent)
  val channelsFlow = channelVM.channels.collectAsState()
  val channels by channelsFlow.value.collectAsState(initial = listOf())

  LaunchedEffect(key1 = Unit) {
    channelVM.allChannels()
  }

  var expandCollapseModel by remember {
    mutableStateOf(
      ExpandCollapseModel(
      1, recent,
      needsPlusButton = false,
      isOpen = true
    )
    )
  }
  SKExpandCollapseColumn(expandCollapseModel, onItemClick, {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  }, channels, onClickAdd)
}