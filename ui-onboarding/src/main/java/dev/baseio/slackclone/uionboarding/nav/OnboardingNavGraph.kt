package dev.baseio.slackclone.uionboarding.nav


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dev.baseio.slackclone.commonui.theme.AlphaNearOpaque
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator
import dev.baseio.slackclone.navigator.Screen
import dev.baseio.slackclone.uionboarding.EmailAddressInputUI
import dev.baseio.slackclone.uionboarding.compose.GettingStartedUI
import dev.baseio.slackclone.uionboarding.compose.SkipTypingUI
import dev.baseio.slackclone.uionboarding.WorkspaceInputUI

@Composable
fun OnboardingNavGraph(
  composeNavigator: ComposeNavigator,
  fragmentNavigator: FragmentNavGraphNavigator
) {
  ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
    SlackCloneSurface(
      color = SlackCloneTheme.colors.statusBarColor.copy(alpha = AlphaNearOpaque),
      modifier = Modifier.fillMaxSize()
    ) {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        composeNavigator.handleNavigationCommands(navController)
      }
      NavHost(
        navController = navController,
        startDestination = Screen.GettingStarted.route
      ) {
        composable(Screen.GettingStarted.route) {
          GettingStartedUI(composeNavigator)
        }
        composable(Screen.SkipTypingScreen.route) {
          SkipTypingUI(composeNavigator)
        }
        composable(Screen.WorkspaceInputUI.route) {
          WorkspaceInputUI(composeNavigator, fragmentNavigator)
        }
        composable(Screen.EmailAddressInputUI.route) {
          EmailAddressInputUI(composeNavigator, fragmentNavigator)
        }
      }
    }
  }
}