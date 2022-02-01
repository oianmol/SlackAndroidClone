package dev.baseio.slackclone.uidashboard.home.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.keyboard.Keyboard
import dev.baseio.slackclone.commonui.keyboard.keyboardAsState
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchCancel() {
  val keyboardController = LocalSoftwareKeyboardController.current

  Row {
    val isKeyboardOpen by keyboardAsState()
    var search by remember { mutableStateOf("") }

    SearchMessagesTF(modifier = Modifier.weight(1f), search) { newValue ->
      search = newValue
    }
    AnimatedVisibility(visible = isKeyboardOpen is Keyboard.Opened) {
      TextButton(onClick = {
        search = ""
        keyboardController?.hide()
      }) {
        Text(
          "Cancel",
          style = SlackCloneTypography.subtitle1.copy(color = Color.White)
        )
      }
    }
  }
}

@Composable
private fun SearchMessagesTF(modifier: Modifier, search: String, onValueChange: (String) -> Unit) {
  BasicTextField(
    value = search,
    singleLine = true,
    maxLines = 1,
    onValueChange = { newSearch ->
      onValueChange(newSearch)
    },
    textStyle = SlackCloneTypography.subtitle1.copy(
      color = Color.White,
    ),
    decorationBox = { innerTextField ->
      Row(
        Modifier
          .background(
            color = Color.White.copy(alpha = 0.2f),
            shape = RoundedCornerShape(12.dp)
          )
          .padding(8.dp), verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = null,
          tint = Color.White
        )
        if (search.isEmpty()) {
          Text(
            "Search for messages and files",
            style = SlackCloneTypography.subtitle1.copy(
              color = Color.White,
            ),
            modifier = Modifier.weight(1f)
          )
        } else {
          innerTextField()
        }

      }
    },
    modifier = modifier.padding(8.dp),
    cursorBrush = SolidColor(Color.White),
  )

}

