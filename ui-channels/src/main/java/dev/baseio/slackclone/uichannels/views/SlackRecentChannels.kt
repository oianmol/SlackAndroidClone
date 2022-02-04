package dev.baseio.slackclone.uichannels.views

import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.uichannels.SlackChannelVM
import dev.baseio.slackclone.chatcore.data.ExpandCollapseModel
import dev.baseio.slackclone.uichannels.R
import androidx.compose.runtime.*

@Composable
fun SlackRecentChannels(
  onItemClick: (ChatPresentation.SlackChannel) -> Unit = {},
  channelVM: SlackChannelVM,
  onClickAdd: () -> Unit
) {
  val recent = stringResource(R.string.Recent)
  val channels by channelVM.channels.collectAsState(initial = emptyList())
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