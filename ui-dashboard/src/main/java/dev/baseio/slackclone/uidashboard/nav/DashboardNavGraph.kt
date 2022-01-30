package dev.baseio.slackclone.uidashboard.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator
import dev.baseio.slackclone.navigator.Screen
import dev.baseio.slackclone.uidashboard.compose.DashboardUI

@Composable
fun DashboardNavGraph(
  composeNavigator: ComposeNavigator
) {
  ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
    SlackCloneSurface(
      modifier = Modifier.fillMaxSize()
    ) {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        composeNavigator.handleNavigationCommands(navController)
      }
      NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
      ) {
        composable(Screen.Dashboard.route){
          DashboardUI()
        }

      }
    }
  }
}