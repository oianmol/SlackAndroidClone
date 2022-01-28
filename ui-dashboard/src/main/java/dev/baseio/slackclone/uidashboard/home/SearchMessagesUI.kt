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
import dev.baseio.slackclone.commonui.keyboard.Keyboard
import dev.baseio.slackclone.commonui.keyboard.keyboardAsState
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTopAppBar() {
  val keyboardController = LocalSoftwareKeyboardController.current
  SlackSurfaceAppBar(
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
    contentPadding = PaddingValues(8.dp)
  ) {
    val isKeyboardOpen by keyboardAsState()
    var search by remember { mutableStateOf("") }

    Row {
      SearchMessagesTF(modifier = Modifier.weight(1f), search) { newValue ->
        search = newValue
      }
      AnimatedVisibility(visible = isKeyboardOpen == Keyboard.Opened) {
        TextButton(onClick = {
          search = ""
          keyboardController?.hide()
        }) {
          Text(
            "Cancel",
            style = SlackCloneTypography.subtitle2.copy(color = Color.White)
          )
        }
      }
    }
  }

}

@Composable
private fun SearchMessagesTF(modifier: Modifier, search: String, onValueChange: (String) -> Unit) {

  TextField(
    value = search,
    singleLine = true,
    maxLines = 1,
    onValueChange = { newSearch ->
      onValueChange(newSearch)
    },
    textStyle = SlackCloneTypography.subtitle2.copy(
      color = Color.White,
    ),
    placeholder = {
      Text(
        "Search for messages and files",
        style = SlackCloneTypography.subtitle2.copy(
          color = Color.White,
        )
      )
    },
    leadingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null,
        tint = Color.White
      )
    },
    modifier = modifier
      .background(
        color = Color.White.copy(alpha = 0.2f),
        shape = RoundedCornerShape(12.dp)
      ),
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = Color.Transparent,
      cursorColor = SlackCloneColorProvider.colors.textPrimary,
      unfocusedIndicatorColor = Color.Transparent,
      focusedIndicatorColor = Color.Transparent
    ),
  )

}
