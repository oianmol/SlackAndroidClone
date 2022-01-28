package dev.baseio.slackclone.uidashboard.home.channels

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import dev.baseio.slackclone.uidashboard.R
import dev.baseio.slackclone.uidashboard.home.channels.data.ExpandCollapseModel

@Composable
fun SlackDirectMessages(
) {
  val recent = stringResource(R.string.direct_messages)
  var expandCollapseModel by remember {
    mutableStateOf(ExpandCollapseModel(
      1, recent,
      needsPlusButton = true,
      isOpen = false
    ))
  }
  SKExpandCollapseColumn(expandCollapseModel) {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  }
}