package com.praxis.feat.authentication.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.mutualmobile.praxis.commonui.theme.AlphaNearOpaque
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.Screen
import com.praxis.feat.authentication.ui.AuthenticationUI
import com.praxis.feat.authentication.ui.ForgotPasswordUI

@Composable
fun AuthNavGraph(
  navigator: ComposeNavigator
) {
  ProvideWindowInsets {
    PraxisSurface(
      color = PraxisTheme.colors.statusBarColor.copy(alpha = AlphaNearOpaque),
      modifier = Modifier.fillMaxSize()
    ) {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        navigator.handleNavigationCommands(navController)
      }
      NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
      ) {
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

