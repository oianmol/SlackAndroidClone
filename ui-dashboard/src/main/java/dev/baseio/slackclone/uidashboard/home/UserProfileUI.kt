package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.reusable.SlackImageBox
import dev.baseio.slackclone.commonui.reusable.SlackListItem
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.SlackScreen
import dev.baseio.slackclone.uidashboard.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserProfileUI(composeNavigator: ComposeNavigator) {
  SlackCloneSurface(
    color = SlackCloneColorProvider.colors.uiBackground,
    modifier = Modifier.fillMaxSize()
  ) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
      SearchTopAppBar()
      UserHeader()
      Box(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        StatusBox()
      }
      SlackListItem(Icons.Default.Notifications, stringResource(R.string.pause_notifications))
      SlackListItem(Icons.Default.Person, stringResource(R.string.set_away))
      Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
      SlackListItem(Icons.Default.FavoriteBorder, stringResource(R.string.saved_items))
      SlackListItem(Icons.Default.Person, stringResource(R.string.view_profile))
      SlackListItem(Icons.Default.Notifications, stringResource(R.string.notifications))
      SlackListItem(Icons.Default.Settings, stringResource(R.string.preferences), onItemClick = {
        composeNavigator.navigate(SlackScreen.SlackPreferences.name)
      })
    }
  }
}

@Composable
private fun SearchTopAppBar() {
  SlackSurfaceAppBar(
    title = {
      Text(
        text = "You",
        style = SlackCloneTypography.h5.copy(
          color = Color.White,
          fontWeight = FontWeight.Bold
        )
      )
    },
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
  )
}


@Composable
fun SlackListItemTrailingView(
  icon: ImageVector,
  title: String,
  trailingView: @Composable () -> Unit = {}
) {
  Row(modifier = Modifier
    .padding(12.dp)
    .clickable { }) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = SlackCloneColorProvider.colors.textPrimary.copy(alpha = 0.4f),
      modifier = Modifier
        .size(24.dp)
        .padding(4.dp)
    )
    Text(
      text = title,
      style = SlackCloneTypography.subtitle1.copy(
        color = SlackCloneColorProvider.colors.textPrimary.copy(
          alpha = 0.8f
        )
      ), modifier = Modifier
        .weight(1f)
        .padding(4.dp)
    )
    trailingView()
  }
}



@Composable
fun UserHeader() {
  Row(Modifier.padding(12.dp)) {
    SlackImageBox(
      Modifier.size(72.dp),
      "https://ca.slack-edge.com/T02TLUWLZ-U2ZG961MW-176c142f9265-512"
    )
    Column(Modifier.padding(start = 8.dp)) {
      Text(text = "Anmol Verma", style = SlackCloneTypography.h6.copy(fontWeight = FontWeight.Bold))
      Spacer(modifier = Modifier.padding(top = 4.dp))
      Text(
        text = "Active",
        style = SlackCloneTypography.subtitle1.copy(
          fontWeight = FontWeight.Bold,
          color = SlackCloneColorProvider.colors.textPrimary.copy(alpha = 0.4f)
        )
      )
    }
  }
}

@Composable
fun StatusBox() {
  RoundedCornerBoxDecoration {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text("🌴", modifier = Modifier.padding(2.dp), style = SlackCloneTypography.subtitle1)
      Text(
        text = "Out on a vacation", style = SlackCloneTypography.body1.copy(
          fontWeight = FontWeight.Normal,
          color = SlackCloneColorProvider.colors.textPrimary
        ), modifier = Modifier
          .padding(2.dp)
          .weight(1f),
        textAlign = TextAlign.Start
      )
      Icon(
        imageVector = Icons.Default.Clear,
        contentDescription = null,
        tint = SlackCloneColorProvider.colors.textPrimary
      )
    }
  }
}


@Composable
fun RoundedCornerBoxDecoration(content: @Composable () -> Unit) {
  Box(
    Modifier
      .border(
        width = 1.dp,
        color = SlackCloneColorProvider.colors.lineColor,
        shape = RoundedCornerShape(12.dp)
      )
      .padding(12.dp)
  ) {
    content()
  }
}