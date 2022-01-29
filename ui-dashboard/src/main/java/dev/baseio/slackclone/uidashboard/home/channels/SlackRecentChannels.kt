package dev.baseio.slackclone.uidashboard.home.channels

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import dev.baseio.slackclone.uidashboard.R
import dev.baseio.slackclone.uidashboard.home.channels.data.ExpandCollapseModel

@Composable
fun SlackRecentChannels(
  onItemClick: () -> Unit = {}
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