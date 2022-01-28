package dev.baseio.slackclone.uionboarding.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator
import dev.baseio.slackclone.uionboarding.R

@Composable
fun CommonInputUI(
  composeNavigator: ComposeNavigator,
  fragmentNavigator: FragmentNavGraphNavigator,
  TopView: @Composable (modifier: Modifier) -> Unit,
  subtitleText: String
) {
  val scaffoldState = rememberScaffoldState()

  SlackCloneTheme {
    Scaffold(
      backgroundColor = SlackCloneColorProvider.colors.darkBackground,
      contentColor = SlackCloneColorProvider.colors.textSecondary,
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
          color = SlackCloneColorProvider.colors.darkBackground,
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
            },fragmentNavigator)
          }
        }
      }

    }
  }
}

@Composable
fun NextButton(modifier: Modifier = Modifier, fragmentNavigator: FragmentNavGraphNavigator) {
  Button(
    onClick = {
      fragmentNavigator.navigateFragment(R.id.action_onboarding_fragment_to_dashboard_fragment)
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
      .wrapContentWidth(align = Alignment.Start),
    style = SlackCloneTypography.subtitle2.copy(
      color = Color.White.copy(alpha = 0.8f),
      fontWeight = FontWeight.Normal,
      textAlign = TextAlign.Start
    )
  )
}

