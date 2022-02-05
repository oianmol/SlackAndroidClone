package dev.baseio.slackclone.uidashboard.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.SlackRoute
import dev.baseio.slackclone.navigator.SlackScreen
import dev.baseio.slackclone.uichannels.create.CreateNewChannelUI
import dev.baseio.slackclone.uichannels.create.SearchCreateChannelUI
import dev.baseio.slackclone.uichat.NewChatThreadScreen
import dev.baseio.slackclone.uidashboard.compose.DashboardUI

fun NavGraphBuilder.dashboardNavigation(
  composeNavigator: ComposeNavigator,
) {
  navigation(
    startDestination = SlackScreen.Dashboard.name,
    route = SlackRoute.Dashboard.name
  ) {
    composable(SlackScreen.Dashboard.name) {
      DashboardUI(composeNavigator)
    }
    composable(SlackScreen.CreateChannelsScreen.name) {
      SearchCreateChannelUI(composeNavigator = composeNavigator)
    }
    composable(SlackScreen.CreateNewChannel.name) {
      CreateNewChannelUI(composeNavigator)
    }
    composable(SlackScreen.CreateNewDM.name) {
      NewChatThreadScreen(composeNavigator)
    }
  }
}