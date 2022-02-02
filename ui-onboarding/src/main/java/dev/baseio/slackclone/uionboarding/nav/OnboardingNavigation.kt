package dev.baseio.slackclone.uionboarding.nav


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.Route
import dev.baseio.slackclone.navigator.Screen
import dev.baseio.slackclone.uionboarding.compose.EmailAddressInputUI
import dev.baseio.slackclone.uionboarding.compose.GettingStartedUI
import dev.baseio.slackclone.uionboarding.compose.SkipTypingUI
import dev.baseio.slackclone.uionboarding.compose.WorkspaceInputUI

fun NavGraphBuilder.onboardingNavigation(
  composeNavigator: ComposeNavigator,
) {
  navigation(
    startDestination = Screen.GettingStarted.name,
    route = Route.OnBoarding.name
  ) {
    composable(Screen.GettingStarted.name) {
      GettingStartedUI(composeNavigator)
    }
    composable(Screen.SkipTypingScreen.name) {
      SkipTypingUI(composeNavigator)
    }
    composable(Screen.WorkspaceInputUI.name) {
      WorkspaceInputUI(composeNavigator)
    }
    composable(Screen.EmailAddressInputUI.name) {
      EmailAddressInputUI(composeNavigator)
    }
  }

}