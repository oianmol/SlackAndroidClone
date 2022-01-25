package dev.baseio.slackclone.uionboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.Screen

@Composable
fun GettingStartedUI(composeNavigator: ComposeNavigator) {
  val scaffoldState = rememberScaffoldState()

  Scaffold(
    backgroundColor = SlackCloneTheme.colors.uiBackground,
    contentColor = SlackCloneTheme.colors.textSecondary,
    modifier = Modifier.statusBarsPadding(), scaffoldState = scaffoldState, snackbarHost = {
      scaffoldState.snackbarHostState
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      SlackCloneSurface(
        color = SlackCloneColor,
        modifier = Modifier

          .padding(28.dp)
      ) {
        Column(
          verticalArrangement = Arrangement.SpaceAround,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
        ) {
          IntroText(modifier = Modifier.padding(top = 12.dp))
          Image(
            painter = painterResource(id = R.drawable.gettingstarted),
            contentDescription = "Logo",
            Modifier
          )
          Spacer(Modifier.padding(8.dp))
          GetStartedButton(composeNavigator)
        }

      }
    }

  }
}

@Composable
private fun GetStartedButton(composeNavigator: ComposeNavigator) {
  Button(
    onClick = {
      composeNavigator.navigate(Screen.Auth.route)
    },
    Modifier
      .fillMaxWidth()
      .height(40.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = SlackCloneTheme.colors.buttonColor)
  ) {
    Text(
      text = "Get started",
      style = SlackCloneTypography.subtitle2.copy(color = SlackCloneTheme.colors.buttonTextColor)
    )
  }
}

@Composable
private fun IntroText(modifier: Modifier = Modifier) {
  Text(
    text = buildAnnotatedString {
      withStyle(
        style = SpanStyle(
          fontFamily = slackFontFamily,
          fontWeight = FontWeight.Bold
        )
      ) {
        append("Picture this: a\n")
      }
      withStyle(
        style = SpanStyle(
          fontFamily = slackFontFamily,
          fontWeight = FontWeight.Bold
        )
      ) {
        append("messaging app,\n")
      }
      withStyle(
        style = SpanStyle(
          SlackLogoYellow,
          fontFamily = slackFontFamily, fontWeight = FontWeight.Bold
        )
      ) {
        append("but built for\nwork.")
      }
    },
    textAlign = TextAlign.Left,
    modifier = modifier.fillMaxWidth(),
    style = SlackCloneTypography.h4
  )
}
