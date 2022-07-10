package dev.baseio.slackclone.uichannels.views

import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.baseio.slackclone.uichannels.SlackChannelVM
import dev.baseio.slackclone.chatcore.data.ExpandCollapseModel
import dev.baseio.slackclone.uichannels.R
import androidx.compose.runtime.*
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.baseio.slackclone.chatcore.data.UiLayerChannels

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SlackStarredChannels(
  onItemClick: (UiLayerChannels.SlackChannel) -> Unit = {},
  channelVM: SlackChannelVM = hiltViewModel(),
  onClickAdd: () -> Unit
) {
  val recent = stringResource(R.string.starred)
  val channelsFlow = channelVM.channels.collectAsStateWithLifecycle()
  val channels by channelsFlow.value.collectAsStateWithLifecycle(initialValue = listOf())

  LaunchedEffect(key1 = Unit) {
    channelVM.allChannels()
  }


  LaunchedEffect(key1 = Unit) {
    channelVM.loadStarredChannels()
  }
  var expandCollapseModel by remember {
    mutableStateOf(
      ExpandCollapseModel(
        1, recent,
        needsPlusButton = false,
        isOpen = false
      )
    )
  }
  SKExpandCollapseColumn(expandCollapseModel, onItemClick, {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  }, channels, onClickAdd)
}