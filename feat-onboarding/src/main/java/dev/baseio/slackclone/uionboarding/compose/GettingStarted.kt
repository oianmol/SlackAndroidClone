package dev.baseio.slackclone.uionboarding.compose

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.SlackScreen
import dev.baseio.slackclone.uionboarding.R

@Composable
fun GettingStartedUI(composeNavigator: ComposeNavigator) {
  SlackCloneTheme {
    val scaffoldState = rememberScaffoldState()
    val sysUiController = rememberSystemUiController()

    SideEffect {
      sysUiController.setNavigationBarColor(color = SlackCloneColor)
      sysUiController.setSystemBarsColor(color = SlackCloneColor)
    }

    var showSlackAnim by remember {
      mutableStateOf(true)
    }

    Scaffold(
      backgroundColor = SlackCloneColor,
      contentColor = SlackCloneColorProvider.colors.textSecondary,
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


          if (showSlackAnim) {
            SlackAnimation {
              showSlackAnim = false
            }
          } else {
            AnimatedVisibility(visible = !showSlackAnim) {
              Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                  .fillMaxHeight()
                  .fillMaxWidth()
              ) {
                IntroText(modifier = Modifier.padding(top = 12.dp))
                CenterImage()
                Spacer(Modifier.padding(8.dp))
                GetStartedButton(composeNavigator)
              }
            }
          }
        }
      }

    }
  }
}

@Composable
private fun CenterImage() {

  var expanded by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    expanded = !expanded
  }
  AnimatedVisibility(
    visible = expanded, enter = ImageEnterTransition(),
    exit = ImageExitTrans()
  ) {
    Image(
      painter = painterResource(id = R.drawable.gettingstarted),
      contentDescription = "Logo",
      Modifier
    )
  }
}

@Composable
private fun ImageExitTrans() = shrinkOut() + fadeOut()

@Composable
private fun ImageEnterTransition() = expandIn(
  expandFrom = Alignment.Center
) + fadeIn(
  // Fade in with the initial alpha of 0.3f.
  initialAlpha = 0.3f
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun GetStartedButton(composeNavigator: ComposeNavigator) {
  var expanded by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    expanded = !expanded
  }
  val density = LocalDensity.current

  AnimatedVisibility(
    visible = expanded, enter = GetStartedEnterTransition(density),
    exit = GetStartedExitTrans()
  ) {
    Button(
      onClick = {
        composeNavigator.navigate(SlackScreen.SkipTypingScreen.name)
      },
      Modifier
        .fillMaxWidth()
        .height(40.dp),
      colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
      Text(
        text = "Get started",
        style = SlackCloneTypography.subtitle2.copy(color = SlackCloneColor)
      )
    }
  }
}

@Composable
private fun GetStartedExitTrans() = slideOutVertically() + shrinkVertically() + fadeOut()

@Composable
private fun GetStartedEnterTransition(density: Density) =
  slideInVertically {
    // Slide in from 40 dp from the bottom.
    with(density) { +5680.dp.roundToPx() }
  } + expandVertically(
    // Expand from the top.
    expandFrom = Alignment.Top
  ) + fadeIn(
    // Fade in with the initial alpha of 0.3f.
    initialAlpha = 0.3f
  )

@Composable
private fun IntroText(modifier: Modifier = Modifier) {
  var expanded by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    expanded = !expanded
  }
  val density = LocalDensity.current

  AnimatedVisibility(
    visible = expanded, enter = IntroEnterTransition(density),
    exit = IntroExitTransition()
  ) {
    Text(
      text = buildAnnotatedString {
        withStyle(
          style = SpanStyle(
            fontFamily = slackFontFamily,
            fontWeight = FontWeight.Bold, color = Color.White
          )
        ) {
          append("Picture this: a\n")
        }
        withStyle(
          style = SpanStyle(
            fontFamily = slackFontFamily,
            fontWeight = FontWeight.Bold, color = Color.White
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

}

@Composable
private fun IntroExitTransition() = slideOutHorizontally() + shrinkHorizontally() + fadeOut()

@Composable
private fun IntroEnterTransition(density: Density) = slideInHorizontally {
  // Slide in from 12580 dp from the left.
  with(density) { -12580.dp.roundToPx() }
} + expandHorizontally(
  // Expand from the top.
  expandFrom = Alignment.Start
) + fadeIn(
  // Fade in with the initial alpha of 0.3f.
  initialAlpha = 0.3f
)
