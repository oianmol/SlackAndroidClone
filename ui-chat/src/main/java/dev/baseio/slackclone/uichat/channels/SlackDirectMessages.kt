package dev.baseio.slackclone.uichat.channels

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import dev.baseio.slackclone.uichat.channels.data.ExpandCollapseModel

@Composable
fun SlackDirectMessages(
  onItemClick: () -> Unit = {}
) {
  val recent = stringResource(R.string.direct_messages)
  var expandCollapseModel by remember {
    mutableStateOf(ExpandCollapseModel(
      1, recent,
      needsPlusButton = true,
      isOpen = false
    ))
  }
  SKExpandCollapseColumn(expandCollapseModel, onItemClick) {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  }
}