package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.DarkBackground
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uidashboard.compose.SlackImageBox

@Composable
fun UserProfileUI() {
  SlackCloneSurface(color = DarkBackground, modifier = Modifier.fillMaxSize()) {
    Column(Modifier.padding(12.dp)) {
      UserHeader()
      StatusBox()
    }
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
        ), modifier = Modifier.padding(4.dp).weight(1f),
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
        color = Color.White.copy(alpha = 0.2f),
        shape = RoundedCornerShape(12.dp)
      )
      .padding(16.dp)
  ) {
    content()
  }
}