package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import dev.baseio.slackclone.commonui.theme.DarkBackground
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uidashboard.R
import dev.baseio.slackclone.uidashboard.compose.SlackImageBox

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserProfileUI() {
  SlackCloneSurface(color = DarkBackground, modifier = Modifier.fillMaxSize()) {
    Column() {
      SearchTopAppBar()
      UserHeader()
      StatusBox()
      ProfileListItem(Icons.Default.Notifications, stringResource(R.string.pause_notifications))
      ProfileListItem(Icons.Default.Person, stringResource(R.string.set_away))
      Divider(color = Color.White.copy(alpha = 0.4f))
      ProfileListItem(Icons.Default.FavoriteBorder, stringResource(R.string.saved_items))
      ProfileListItem(Icons.Default.Person, stringResource(R.string.view_profile))
      ProfileListItem(Icons.Default.Notifications, stringResource(R.string.notifications))
      ProfileListItem(Icons.Default.Settings, stringResource(R.string.preferences))
    }
  }
}

@Composable
private fun SearchTopAppBar() {
  SlackSurfaceAppBar(
    title = {
      Text(text = "You", style = SlackCloneTypography.h5.copy(color = Color.White, fontWeight = FontWeight.Bold))
    },
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
  )
}


@ExperimentalMaterialApi
@Composable
private fun ProfileListItem(icon: ImageVector, title: String) {
  ListItem(icon = {
    Icon(imageVector = icon, contentDescription = null, tint = Color.White.copy(alpha = 0.4f))
  }, text = {
    Text(
      text = title,
      style = SlackCloneTypography.subtitle1.copy(color = Color.White)
    )
  })
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
          color = Color.White.copy(alpha = 0.4f)
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
      Text("🌴", modifier = Modifier.padding(4.dp), style = SlackCloneTypography.subtitle1)
      Text(
        text = "Out on a vacation", style = SlackCloneTypography.body1.copy(
          fontWeight = FontWeight.Normal,
          color = Color.White
        ), modifier = Modifier
          .padding(4.dp)
          .weight(1f),
        textAlign = TextAlign.Start
      )
      Icon(imageVector = Icons.Default.Clear, contentDescription = null, tint = Color.LightGray)
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
      .padding(16.dp)
  ) {
    content()
  }
}