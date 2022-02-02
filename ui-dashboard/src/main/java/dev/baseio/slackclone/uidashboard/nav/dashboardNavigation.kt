package dev.baseio.slackclone.uidashboard.nav

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.Route
import dev.baseio.slackclone.navigator.Screen
import dev.baseio.slackclone.uidashboard.compose.DashboardUI

fun NavGraphBuilder.dashboardNavigation(
  composeNavigator: ComposeNavigator,
) {
  navigation(
    startDestination = Screen.Dashboard.name,
    route = Route.Dashboard.name
  ) {
    composable(Screen.Dashboard.name) {
      DashboardUI(composeNavigator)
    }
  }
}