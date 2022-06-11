package dev.baseio.slackclone.uidashboard.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.SlackRoute
import dev.baseio.slackclone.navigator.SlackScreen
import dev.baseio.slackclone.uichannels.createsearch.CreateNewChannelUI
import dev.baseio.slackclone.uichannels.createsearch.SearchCreateChannelUI
import dev.baseio.slackclone.uichat.newchat.NewChatThreadScreen
import dev.baseio.slackclone.uidashboard.compose.DashboardUI
import dev.baseio.slackclone.uidashboard.home.preferences.PreferencesUI

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
    composable(SlackScreen.SlackPreferences.name) {
      PreferencesUI(composeNavigator = composeNavigator)
    }
  }
}