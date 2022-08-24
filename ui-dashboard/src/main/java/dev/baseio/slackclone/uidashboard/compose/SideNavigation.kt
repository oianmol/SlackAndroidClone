package dev.baseio.slackclone.uidashboard.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.uidashboard.R
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.reusable.SlackImageBox
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.commonui.reusable.SlackListItem
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.SlackScreen

@Composable
fun SideNavigation(modifier: Modifier, composeNavigator: ComposeNavigator) {
  SlackCloneSurface(color = SlackCloneColorProvider.colors.uiBackground , modifier = modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        WorkspacesBar()
        Spacer(modifier = Modifier.padding(8.dp))
        Workspace(selected = true)
        Spacer(modifier = Modifier.padding(8.dp))
        Workspace(selected = false)
      }
      Spacer(modifier = Modifier.padding(8.dp))
      SideNavFooter(composeNavigator)
    }

  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SideNavFooter(composeNavigator: ComposeNavigator) {
  Column(modifier = Modifier.navigationBarsPadding()) {
    Divider(color = SlackCloneColorProvider.colors.lineColor)
    SlackListItem(Icons.Filled.AddCircle, stringResource(id = R.string.add_workspace))
    SlackListItem(Icons.Filled.Settings, stringResource(id = R.string.preferences))
    SlackListItem(Icons.Filled.CheckCircle, stringResource(id = R.string.help))
  }
}

@Composable
fun Workspace(selected: Boolean) {
  Box(
    Modifier.background(
      color = if (selected) SlackCloneColorProvider.colors.textPrimary.copy(alpha = 0.2f) else Color.Transparent,
      shape = RoundedCornerShape(12.dp)
    )
  ) {
    Row(
      modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
      OrganizationLogo()
      Box(Modifier.weight(1f)) {
        OrganizationDetails()
      }
      Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null, tint = SlackCloneColorProvider.colors.textPrimary)
    }
  }
}

@Composable
fun OrganizationDetails() {
  Column(
    modifier = Modifier
      .padding(start = 8.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start
  ) {
    Text(
      text = stringResource(R.string.mutualmobile),
      style = SlackCloneTypography.h6.copy(color = SlackCloneColorProvider.colors.textPrimary, fontWeight = FontWeight.SemiBold)
    )
    Text(
      stringResource(R.string.mmlink),
      style = SlackCloneTypography.subtitle1.copy(
        fontWeight = FontWeight.Normal,
        color = SlackCloneColorProvider.colors.textPrimary.copy(alpha = 0.4f)
      ),
    )
  }
}

@Composable
fun OrganizationLogo() {
  Box(
    Modifier
      .size(68.dp)
      .border(
        width = 3.dp,
        color = SlackCloneColorProvider.colors.textPrimary,
        shape = RoundedCornerShape(12.dp)
      )
      .padding(8.dp)
  ) {
    SlackImageBox(
      Modifier.size(64.dp),
      "https://avatars.slack-edge.com/2018-07-20/401750958992_1b07bb3c946bc863bfc6_88.png"
    )
  }
}

@Composable
private fun WorkspacesBar() {
  SlackSurfaceAppBar(
    backgroundColor = SlackCloneColorProvider.colors.uiBackground,
    elevation = 0.dp,
    contentPadding = WindowInsets.statusBars.asPaddingValues()
  ) {
    Text(
      text = stringResource(id = R.string.head_workspaces),
      style = SlackCloneTypography.h5.copy(
        color = SlackCloneColorProvider.colors.textPrimary,
        fontFamily = slackFontFamily,
        fontWeight = FontWeight.Bold
      ),
      modifier = Modifier.padding(start = 8.dp)
    )
  }
}