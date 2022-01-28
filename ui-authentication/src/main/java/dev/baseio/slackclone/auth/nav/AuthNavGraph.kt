package dev.baseio.slackclone.auth.nav

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
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.Screen
import dev.baseio.slackclone.auth.ui.AuthenticationUI
import dev.baseio.slackclone.auth.ui.ForgotPasswordUI
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator

@Composable
fun AuthNavGraph(
  composeNavigator: ComposeNavigator,
  navigatorFragment: FragmentNavGraphNavigator
) {
  ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
    SlackCloneSurface(
      color = SlackCloneColorProvider.colors.statusBarColor.copy(alpha = AlphaNearOpaque),
      modifier = Modifier.fillMaxSize()
    ) {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        composeNavigator.handleNavigationCommands(navController)
      }
      NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
      ) {
        composable(Screen.Auth.route) {
          AuthenticationUI(
            composeNavigator = composeNavigator,
            navigatorFragment = navigatorFragment
          )
        }
        composable(Screen.ForgotPassword.route) {
          ForgotPasswordUI(
            composeNavigator = composeNavigator,
            navigatorFragment = navigatorFragment
          )
        }
      }
    }
  }
}

