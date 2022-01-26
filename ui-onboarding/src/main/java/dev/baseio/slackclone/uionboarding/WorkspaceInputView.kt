package dev.baseio.slackclone.uionboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@Composable
fun WorkspaceInputView(modifier: Modifier) {
  Row(modifier = modifier.fillMaxWidth()) {
    Text(text = "https://", style = textStyleField())
    WorkspaceTF()
    Text("slack.com", style = textStyleField())
  }
}


@Composable
private fun WorkspaceTF() {
  var workspace by remember { mutableStateOf("") }

  TextField(
    value = workspace,
    onValueChange = { newEmail ->
      workspace = newEmail
    },
    textStyle = textStyleField(),
    placeholder = {
      Text(
        text = "your-workspace",
        style = textStyleField(),
        textAlign = TextAlign.Start
      )
    },
    colors = textFieldColors(),
    singleLine = true,
    maxLines = 1
  )
}

@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
  backgroundColor = Color.Transparent,
  cursorColor = Color.White,
  unfocusedIndicatorColor = Color.Transparent,
  focusedIndicatorColor = Color.Transparent
)

@Composable
private fun textStyleField() = SlackCloneTypography.h6.copy(
  color = Color.White.copy(alpha = 0.7f),
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start
)