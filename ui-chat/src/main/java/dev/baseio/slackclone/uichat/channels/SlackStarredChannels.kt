package dev.baseio.slackclone.uichat.channels

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import dev.baseio.slackclone.uichat.channels.data.ExpandCollapseModel

@Composable
fun SlackStarredChannels(
  onItemClick: () -> Unit = {}
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