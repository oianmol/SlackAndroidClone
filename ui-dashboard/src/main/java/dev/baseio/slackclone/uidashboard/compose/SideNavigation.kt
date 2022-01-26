package dev.baseio.slackclone.uidashboard.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.uidashboard.R
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.*

@Composable
fun SideNavigation() {
  SlackCloneSurface(color = SlackCloneColor, modifier = Modifier.fillMaxSize()) {
    LazyColumn(modifier = Modifier.statusBarsPadding()) {
      item {
        Workspaces()
      }
      items(1) {
        Workspace()
      }
    }
  }
}

@Composable
fun Workspace() {
  Row(modifier = Modifier.padding(8.dp)) {
    OrganizationLogo()
    OrganizationDetails()
  }
}

@Composable
fun OrganizationDetails() {
  Column(modifier = Modifier
    .fillMaxWidth()
    .padding(start = 8.dp)) {
    Text(text = "mutualmobile", style = SlackCloneTypography.h6.copy(color = Color.White))
    Text(
      "mutualmobile.slack.com",
      style = SlackCloneTypography.subtitle2.copy(
        fontWeight = FontWeight.Bold,
        color = Color.White.copy(alpha = 0.4f)
      )
    )
  }
}

@Composable
fun OrganizationLogo() {
  MMImage(Modifier.size(64.dp))
}

@Composable
private fun Workspaces() {
  SlackSurfaceAppBar(title = {
    Text(
      text = stringResource(id = R.string.head_workspaces),
      style = SlackCloneTypography.h5.copy(
        color = Color.White,
        fontFamily = slackFontFamily,
        fontWeight = FontWeight.Bold
      )
    )
  }, backgroundColor = SlackCloneColor)
}