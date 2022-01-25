package com.praxis.feat.onboarding.nav

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
import dev.baseio.slackclone.navigator.Screen
import com.praxis.feat.onboarding.ui.AuthenticationUI
import com.praxis.feat.onboarding.ui.ForgotPasswordUI
import dev.baseio.slackclone.uionboarding.GettingStartedUI

@Composable
fun OnboardingNavGraph(
  navigator: ComposeNavigator
) {
  ProvideWindowInsets {
    SlackCloneSurface(
      color = SlackCloneTheme.colors.statusBarColor.copy(alpha = AlphaNearOpaque),
      modifier = Modifier.fillMaxSize()
    ) {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        navigator.handleNavigationCommands(navController)
      }
      NavHost(
        navController = navController,
        startDestination = Screen.GettingStarted.route
      ) {
        composable(Screen.GettingStarted.route){
          GettingStartedUI(navigator)
        }
        composable(Screen.Auth.route) {
          AuthenticationUI()
        }
        composable(Screen.ForgotPassword.route) {
          ForgotPasswordUI()
        }
      }
    }
  }
}

