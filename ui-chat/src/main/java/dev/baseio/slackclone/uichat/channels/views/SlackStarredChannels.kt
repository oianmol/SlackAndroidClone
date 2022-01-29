package dev.baseio.slackclone.uichat.channels.views

import dev.baseio.slackclone.uichat.R
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.baseio.slackclone.uichat.channels.SlackChannelVM
import dev.baseio.slackclone.uichat.channels.data.ExpandCollapseModel

@Composable
fun SlackStarredChannels(
  onItemClick: () -> Unit = {},
  channelVM: SlackChannelVM = hiltViewModel()
) {
  val recent = stringResource(R.string.starred)
  var expandCollapseModel by remember {
    mutableStateOf(
      ExpandCollapseModel(
        1, recent,
        needsPlusButton = false,
        isOpen = false
      )
    )
  }
  SKExpandCollapseColumn(expandCollapseModel, onItemClick) {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  }
}