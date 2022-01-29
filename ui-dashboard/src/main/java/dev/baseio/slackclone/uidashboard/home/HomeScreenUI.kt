package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.reusable.SlackListItem
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uidashboard.R
import dev.baseio.slackclone.uidashboard.compose.SlackImageBox
import dev.baseio.slackclone.uidashboard.home.channels.*

@Composable
fun HomeScreenUI(appBarIconClick: () -> Unit) {
  SlackCloneSurface(color = SlackCloneColorProvider.colors.uiBackground, modifier = Modifier.fillMaxSize()) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
      SlackMMTopAppBar(appBarIconClick)
      JumpToText()
      ThreadsTile()
      Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
      SlackRecentChannels()
      SlackStarredChannels()
      SlackDirectMessages()
      SlackAllChannels()
      SlackConnections()
    }
  }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThreadsTile() {
  SlackListItem(Icons.Default.MailOutline, stringResource(R.string.threads))
}

@Composable
fun JumpToText() {
  Box(
    Modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {
    RoundedCornerBoxDecoration {
      Text(
        text = "Jump to...",
        style = SlackCloneTypography.subtitle2.copy(color = SlackCloneColorProvider.colors.lineColor),
        modifier = Modifier.fillMaxWidth()
      )
    }
  }

}

@Composable
fun SlackMMTopAppBar(appBarIconClick: () -> Unit) {
  SlackSurfaceAppBar(
    title = {
      Text(text = "mutualmobile", style = SlackCloneTypography.h5.copy(color = Color.White))
    },
    navigationIcon = {
      MMImageButton(appBarIconClick)
    },
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
  )
}

@Composable
fun MMImageButton(appBarIconClick: () -> Unit) {
  IconButton(onClick = {
    appBarIconClick()
  }) {
    SlackImageBox(
      Modifier.size(38.dp),
      "https://avatars.slack-edge.com/2018-07-20/401750958992_1b07bb3c946bc863bfc6_88.png"
    )
  }
}