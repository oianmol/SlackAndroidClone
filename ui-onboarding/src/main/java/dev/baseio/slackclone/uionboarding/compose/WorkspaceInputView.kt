package dev.baseio.slackclone.uionboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@Composable
fun WorkspaceInputView(modifier: Modifier) {
  Column(modifier = modifier
    .fillMaxWidth()
    .wrapContentWidth()) {
    Text(
      text = "Workspace URL", style = SlackCloneTypography.caption.copy(
        color = Color.White.copy(alpha = 0.7f),
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Start
      ), modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
    )
    Row(
      modifier = modifier
        .fillMaxWidth()
        .wrapContentWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start
    ) {
      Text(text = "https://", style = textStyleField())
      Box(modifier = Modifier.fillMaxWidth(0.6f)) {
        WorkspaceTF()
      }
      Text(".slack.com", style = textStyleField())
    }
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