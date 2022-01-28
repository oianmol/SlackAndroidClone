package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.DarkBackground
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uidashboard.compose.SlackImageBox

@Composable
fun HomeScreenUI(appBarIconClick: () -> Unit) {
  SlackCloneSurface(color = DarkBackground, modifier = Modifier.fillMaxSize()) {
    Column {
      SlackMMTopAppBar(appBarIconClick)
      JumpToText()

    }
  }
}

@Composable
fun JumpToText() {
  Box(Modifier.fillMaxWidth().padding(8.dp)) {
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