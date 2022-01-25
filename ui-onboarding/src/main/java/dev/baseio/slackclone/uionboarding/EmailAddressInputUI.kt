package dev.baseio.slackclone.uionboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.navigator.ComposeNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailAddressInputUI(composeNavigator: ComposeNavigator) {
  val scaffoldState = rememberScaffoldState()

  val sysUiController = rememberSystemUiController()

  LaunchedEffect(Unit) {
    sysUiController.setNavigationBarColor(color = DarkBackground)
    sysUiController.setSystemBarsColor(color = DarkBackground)
  }

  Scaffold(
    backgroundColor = SlackCloneTheme.colors.darkBackground,
    contentColor = SlackCloneTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    scaffoldState = scaffoldState,
    snackbarHost = {
      scaffoldState.snackbarHostState
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      SlackCloneSurface(
        color = SlackCloneTheme.colors.darkBackground,
        modifier = Modifier
      ) {
        ConstraintLayout(modifier = Modifier
          .padding(12.dp)
          .fillMaxHeight()
          .fillMaxWidth()) {
          // Create references for the composables to constrain
          val (email, subtitle,button) = createRefs()

          EmailTF(modifier = Modifier.constrainAs(email){
            top.linkTo(parent.top)
            bottom.linkTo(button.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          })
          EmailSubTitle(modifier = Modifier.constrainAs(subtitle){
            top.linkTo(email.bottom)
          })
          NextButton(modifier = Modifier.constrainAs(button){
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          })
        }
      }
    }

  }
}

@Composable
fun NextButton(modifier:Modifier = Modifier) {
  Button(
    onClick = {

    },
    modifier
      .fillMaxWidth()
      .height(50.dp)
      .padding(top = 8.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(alpha = 0.2f))
  ) {
    Text(
      text = "Next",
      style = SlackCloneTypography.subtitle2.copy(color = Color.White)
    )
  }
}

@Composable
private fun EmailSubTitle(modifier:Modifier = Modifier) {
  Text(
    "We'll send you an email that'll instantly sign you in.",
    modifier = modifier
      .fillMaxWidth()
      .padding(start = 16.dp),
    style = SlackCloneTypography.subtitle2.copy(
      color = Color.White.copy(alpha = 0.8f),
      fontWeight = FontWeight.Normal,
      textAlign = TextAlign.Start
    )
  )
}

@ExperimentalComposeUiApi
@Composable
private fun EmailTF(modifier:Modifier = Modifier) {
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