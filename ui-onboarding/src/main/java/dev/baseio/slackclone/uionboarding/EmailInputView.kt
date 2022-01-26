package dev.baseio.slackclone.uionboarding

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailInputView(modifier: Modifier = Modifier) {
  EmailTF(modifier)
}

@ExperimentalComposeUiApi
@Composable
private fun EmailTF(modifier: Modifier = Modifier) {
  var email by remember { mutableStateOf("") }
  val keyboardController = LocalSoftwareKeyboardController.current

  TextField(
    value = email,
    onValueChange = { newEmail ->
      email = newEmail
    },
    textStyle = textStyleField(),
    placeholder = {
      Text(
        text = "Your email address",
        modifier = Modifier.fillMaxWidth(),
        style = textStyleField(),
        textAlign = TextAlign.Start
      )
    },
    keyboardOptions = KeyboardOptions.Default.copy(
      autoCorrect = false,
      keyboardType = KeyboardType.Email,
      imeAction = ImeAction.Done,
    ),
    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
    modifier = modifier
      .fillMaxWidth(),
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