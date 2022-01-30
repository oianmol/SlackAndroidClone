package dev.baseio.slackclone.uichat

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uichat.models.ChatPresentation

@Composable
fun ChatScreenUI(
  modifier: Modifier,
  slackChannel: ChatPresentation.SlackChannel,
  onBackClick: () -> Unit,
  viewModel: ChatThreadVM = hiltViewModel()
) {
  val scaffoldState = rememberScaffoldState()
  SlackCloneTheme {
    Scaffold(
      backgroundColor = SlackCloneColorProvider.colors.uiBackground,
      contentColor = SlackCloneColorProvider.colors.textSecondary,
      modifier = modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
      topBar = {
        ChatAppBar(onBackClick, slackChannel)
      }
    ) { innerPadding ->
      Box(modifier = Modifier.padding(innerPadding)) {

      }
    }
  }
}

@Composable
private fun ChatAppBar(onBackClick: () -> Unit, slackChannel: ChatPresentation.SlackChannel) {
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
      Text(
        text = " ${if (slackChannel.isPrivate) lock() else "#"}  ${slackChannel.name}",
        style = SlackCloneTypography.subtitle1.copy(
          fontWeight = FontWeight.Bold,
          color = SlackCloneColorProvider.colors.appBarTextTitleColor
        ), modifier = Modifier.padding(2.dp)
      )
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