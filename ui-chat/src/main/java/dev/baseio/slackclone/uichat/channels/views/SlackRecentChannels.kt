package dev.baseio.slackclone.uichat.channels

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.baseio.slackclone.uichat.channels.data.ExpandCollapseModel
import dev.baseio.slackclone.uichat.channels.views.SKExpandCollapseColumn
import dev.baseio.slackclone.uichat.R

@Composable
fun SlackRecentChannels(
  onItemClick: () -> Unit = {},
  channelVM: SlackChannelVM = hiltViewModel()
) {
  val recent = stringResource(R.string.Recent)
  // TODO update remember to rememberSaveable
  var expandCollapseModel by remember {
    mutableStateOf(ExpandCollapseModel(
      1, recent,
      needsPlusButton = false,
      isOpen = true
    ))
  }
  SKExpandCollapseColumn(expandCollapseModel,onItemClick) {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  }
}