package dev.baseio.slackclone.uidashboard.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.slackclone.commonui.keyboard.Keyboard
import dev.baseio.slackclone.commonui.keyboard.keyboardAsState
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.DarkBackground
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@Composable
fun SearchMessagesUI() {
  SlackCloneSurface(color = DarkBackground, modifier = Modifier.fillMaxSize()) {
    Column() {
      SearchTopAppBar()
    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTopAppBar() {
  val keyboardController = LocalSoftwareKeyboardController.current
  SlackSurfaceAppBar(
    backgroundColor = SlackCloneTheme.colors.appBarColor,
    contentPadding = PaddingValues(8.dp)
  ) {
    val isKeyboardOpen by keyboardAsState()

    Row {
      SearchMessagesTF(modifier = Modifier.weight(1f))
      AnimatedVisibility(visible = isKeyboardOpen == Keyboard.Opened) {
        TextButton(onClick = {
          keyboardController?.hide()
        }) {
          Text("Cancel", style = SlackCloneTypography.h6.copy(color = Color.White))
        }
      }
    }
  }

}

@Composable
private fun SearchMessagesTF(modifier: Modifier) {
  Box(modifier
    .background(
      color = Color.White.copy(alpha = 0.2f),
      shape = RoundedCornerShape(12.dp)
    )) {
    var search by remember { mutableStateOf("") }

    TextField(
      value = search,
      onValueChange = { newSearch ->
        search = newSearch
      },
      placeholder = {
        Text(
          "Search for messages and files",
          style = SlackCloneTypography.subtitle1.copy(color = Color.White, fontSize = 18.sp)
        )
      },
      leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.White)
      },
      modifier = modifier,
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
        cursorColor = Color.White,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent
      ),
    )
  }

}
