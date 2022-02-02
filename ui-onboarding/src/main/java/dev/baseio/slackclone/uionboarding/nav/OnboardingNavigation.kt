package dev.baseio.slackclone.uionboarding.nav


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.SlackRoute
import dev.baseio.slackclone.navigator.SlackScreen
import dev.baseio.slackclone.uionboarding.compose.EmailAddressInputUI
import dev.baseio.slackclone.uionboarding.compose.GettingStartedUI
import dev.baseio.slackclone.uionboarding.compose.SkipTypingUI
import dev.baseio.slackclone.uionboarding.compose.WorkspaceInputUI

fun NavGraphBuilder.onboardingNavigation(
  composeNavigator: ComposeNavigator,
) {
  navigation(
    startDestination = SlackScreen.GettingStarted.name,
    route = SlackRoute.OnBoarding.name
  ) {
    composable(SlackScreen.GettingStarted.name) {
      GettingStartedUI(composeNavigator)
    }
    composable(SlackScreen.SkipTypingScreen.name) {
      SkipTypingUI(composeNavigator)
    }
    composable(SlackScreen.WorkspaceInputUI.name) {
      WorkspaceInputUI(composeNavigator)
    }
    composable(SlackScreen.EmailAddressInputUI.name) {
      EmailAddressInputUI(composeNavigator)
    }
  }

}