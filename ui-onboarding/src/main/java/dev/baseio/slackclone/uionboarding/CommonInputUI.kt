package dev.baseio.slackclone.uionboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.slackclone.commonui.theme.DarkBackground
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.Screen

@Composable
fun CommonInputUI(
  composeNavigator: ComposeNavigator,
  TopView: @Composable (modifier: Modifier) -> Unit,
  subtitleText: String
) {
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
        ConstraintLayout(
          modifier = Modifier
            .padding(12.dp)
            .navigationBarsWithImePadding()
            .fillMaxHeight()
            .fillMaxWidth()
        ) {
          // Create references for the composables to constrain
          val (inputView, subtitle, button) = createRefs()

          TopView(modifier = Modifier.constrainAs(inputView) {
            top.linkTo(parent.top)
            bottom.linkTo(button.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          })
          SubTitle(modifier = Modifier.constrainAs(subtitle) {
            top.linkTo(inputView.bottom)
          }, subtitleText)
          NextButton(modifier = Modifier.constrainAs(button) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          },composeNavigator)
        }
      }
    }

  }
}

@Composable
fun NextButton(modifier: Modifier = Modifier, composeNavigator: ComposeNavigator) {
  Button(
    onClick = {
      composeNavigator.navigate(Screen.Auth.route)
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
private fun SubTitle(modifier: Modifier = Modifier, subtitleText: String) {
  Text(
    subtitleText,
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

