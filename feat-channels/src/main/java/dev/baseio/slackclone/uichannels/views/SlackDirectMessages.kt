package dev.baseio.slackclone.uichannels.views

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.baseio.slackclone.chatcore.data.ExpandCollapseModel
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.uichannels.R
import dev.baseio.slackclone.uichannels.SlackChannelVM

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SlackDirectMessages(
    onItemClick: (UiLayerChannels.SlackChannel) -> Unit = {},
    channelVM: SlackChannelVM = hiltViewModel(),
    onClickAdd: () -> Unit
) {
    val recent = stringResource(R.string.direct_messages)
    val channelsFlow = channelVM.channels.collectAsStateWithLifecycle()
    val channels by channelsFlow.value.collectAsStateWithLifecycle(initialValue = listOf())

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
