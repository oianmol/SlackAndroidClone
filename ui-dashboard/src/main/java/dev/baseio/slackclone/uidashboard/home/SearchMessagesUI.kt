package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.DarkBackground
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@Composable
fun SearchMessagesUI() {
  SlackCloneSurface(color = DarkBackground, modifier = Modifier.fillMaxSize()) {
    Column() {
      SearchTopAppBar()
    }
  }
}

@Composable
private fun SearchTopAppBar() {
  SlackSurfaceAppBar(
    backgroundColor = DarkBackground,
  ) {
    Box(
      Modifier
        .background(
          color = Color.White.copy(alpha = 0.2f),
          shape = RoundedCornerShape(12.dp)
        ).fillMaxWidth()
    ) {
      SearchMessagesTF()
    }
  }

}

@Composable
private fun SearchMessagesTF() {
  TextField(value = "", onValueChange = {}, placeholder = {
    Text(
      "Search for messages and files",
      style = SlackCloneTypography.subtitle1.copy(color = Color.White)
    )
  }, leadingIcon = {
    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.White)
  }, modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.textFieldColors(
    backgroundColor = Color.Transparent,
    cursorColor = Color.White,
    unfocusedIndicatorColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent
  )
  )
}
