package dev.baseio.slackclone.uidashboard.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.uidashboard.R
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.*
import dev.baseio.slackclone.commonui.theme.*

@Composable
fun SideNavigation(modifier: Modifier) {
  SlackCloneSurface(color = Color(0xff1c1d21) , modifier = modifier.fillMaxSize()) {
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
      SideNavFooter()
    }

  }
}

@Composable
private fun SideNavFooter() {
  Column(modifier = Modifier.navigationBarsPadding()) {
    Divider(color = Color.White.copy(alpha = 0.2f))
    FooterItem(Icons.Filled.AddCircle, stringResource(id = R.string.add_workspace))
    FooterItem(Icons.Filled.Settings, stringResource(id = R.string.preferences))
    FooterItem(Icons.Filled.CheckCircle, stringResource(id = R.string.help))
  }
}

@Composable
fun FooterItem(imageVector: ImageVector, title: String) {
  Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
    Icon(
      imageVector,
      contentDescription = null,
      tint = Color.White.copy(alpha = 0.5f),
      modifier = Modifier.padding(8.dp)
    )
    Text(
      text = title,
      style = SlackCloneTypography.subtitle1.copy(color = Color.White.copy(alpha = 0.8f)),
      modifier = Modifier.padding(8.dp)
    )
  }
}

@Composable
fun Workspace(selected: Boolean) {
  Box(
    Modifier.background(
      color = if (selected) Color.White.copy(alpha = 0.2f) else Color.Transparent,
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
      Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null, tint = Color.White)
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
      style = SlackCloneTypography.h6.copy(color = Color.White, fontWeight = FontWeight.SemiBold)
    )
    Text(
      stringResource(R.string.mmlink),
      style = SlackCloneTypography.subtitle1.copy(
        fontWeight = FontWeight.Normal,
        color = Color.White.copy(alpha = 0.4f)
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
        color = Color.White,
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
    backgroundColor = Color(0xff212429),
    elevation = 0.dp,
    contentPadding = rememberInsetsPaddingValues(
      insets = LocalWindowInsets.current.statusBars,
      applyStart = true,
      applyTop = true,
      applyEnd = true,
    )
  ) {
    Text(
      text = stringResource(id = R.string.head_workspaces),
      style = SlackCloneTypography.h5.copy(
        color = Color.White,
        fontFamily = slackFontFamily,
        fontWeight = FontWeight.Bold
      ),
      modifier = Modifier.padding(start = 8.dp)
    )
  }
}