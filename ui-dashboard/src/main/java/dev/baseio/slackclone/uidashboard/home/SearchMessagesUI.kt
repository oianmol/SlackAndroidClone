package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.uidashboard.home.search.SearchCancel

@Composable
fun SearchMessagesUI() {
  SlackCloneSurface(
    color = SlackCloneColorProvider.colors.uiBackground,
    modifier = Modifier.fillMaxSize()
  ) {
    Column() {
      SearchTopAppBar()
    }
  }
}

@Composable
private fun SearchTopAppBar() {
  SlackSurfaceAppBar(
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
    contentPadding = PaddingValues(8.dp)
  ) {
    SearchCancel()
  }

}

