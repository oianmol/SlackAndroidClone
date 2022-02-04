package dev.baseio.slackclone.uichannels.views

import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.baseio.slackclone.chatcore.data.ExpandCollapseModel
import dev.baseio.slackclone.uichannels.R
import dev.baseio.slackclone.uichannels.SlackChannelVM
import androidx.compose.runtime.*
import dev.baseio.slackclone.chatcore.data.ChatPresentation

@Composable
fun SlackAllChannels(
  onItemClick: (ChatPresentation.SlackChannel) -> Unit = {},
  channelVM: SlackChannelVM,
  onClickAdd: () -> Unit
) {
  val recent = stringResource(R.string.channels)
  val channels by channelVM.channels.collectAsState(initial = emptyList())
  var expandCollapseModel by remember {
    mutableStateOf(
      ExpandCollapseModel(
        1, recent,
        needsPlusButton = true,
        isOpen = false
      )
    )
  }
  SKExpandCollapseColumn(expandCollapseModel = expandCollapseModel, onItemClick = onItemClick, {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  },channels, onClickAdd)
}