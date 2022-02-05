package dev.baseio.slackclone.uichannels.views

import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.baseio.slackclone.chatcore.data.ExpandCollapseModel
import dev.baseio.slackclone.uichannels.R
import dev.baseio.slackclone.uichannels.SlackChannelVM
import androidx.compose.runtime.*
import dev.baseio.slackclone.chatcore.data.ChatPresentation

@Composable
fun SlackDirectMessages(
  onItemClick: (ChatPresentation.SlackChannel) -> Unit = {},
  channelVM: SlackChannelVM = hiltViewModel(),
  onClickAdd: () -> Unit
) {
  val recent = stringResource(R.string.direct_messages)
  val channelsFlow = channelVM.channels.collectAsState()
  val channels by channelsFlow.value.collectAsState(initial = listOf())

  LaunchedEffect(key1 = Unit) {
    channelVM.loadDirectMessageChannels()
  }
  var expandCollapseModel by remember {
    mutableStateOf(
      ExpandCollapseModel(
        1, recent,
        needsPlusButton = true,
        isOpen = false
      )
    )
  }
  SKExpandCollapseColumn(expandCollapseModel, onItemClick, {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  }, channels, onClickAdd)
}
