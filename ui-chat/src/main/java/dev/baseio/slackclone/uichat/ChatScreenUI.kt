package dev.baseio.slackclone.uichat

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.chatcore.views.SlackChannelItem
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uichat.chatscreen.ChatScreenContent

@OptIn(
  ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class,
  androidx.constraintlayout.compose.ExperimentalMotionApi::class
)
@Composable
fun ChatScreenUI(
  modifier: Modifier,
  slackChannel: UiLayerChannels.SlackChannel,
  onBackClick: () -> Unit,
  viewModel: ChatThreadVM
) {
  val scaffoldState = rememberScaffoldState()
  SideEffect {
    viewModel.requestFetch(slackChannel)
  }
  SlackCloneTheme {
    Scaffold(
      backgroundColor = SlackCloneColorProvider.colors.uiBackground,
      contentColor = SlackCloneColorProvider.colors.textSecondary,
      modifier = modifier
        .statusBarsPadding(),
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
      topBar = {
        ChatAppBar(onBackClick, slackChannel)
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .padding(innerPadding)
      ) {
        ChatScreenContent(viewModel)
      }
    }
  }

}


@Composable
private fun ChatAppBar(onBackClick: () -> Unit, slackChannel: UiLayerChannels.SlackChannel) {
  SlackSurfaceAppBar(backgroundColor = SlackCloneColorProvider.colors.appBarColor) {
    IconButton(onClick = { onBackClick() }) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        tint = SlackCloneColorProvider.colors.appBarIconColor,
        modifier = Modifier.size(24.dp)
      )
    }
    Column(
      Modifier.weight(1f),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      SlackChannelItem(slackChannel = slackChannel, {})
      Text(
        text = "25 members >",
        style = SlackCloneTypography.caption.copy(
          fontWeight = FontWeight.Normal,
          color = SlackCloneColorProvider.colors.appBarTextSubTitleColor
        ),
        modifier = Modifier.padding(2.dp)
      )
    }
    IconButton(onClick = { }) {
      Icon(
        imageVector = Icons.Default.Call,
        contentDescription = null,
        tint = SlackCloneColorProvider.colors.appBarIconColor,
        modifier = Modifier
          .size(24.dp)
      )
    }
  }
}

fun lock() = "\uD83D\uDD12"

enum class BoxState { Collapsed, Expanded }