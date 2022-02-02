package dev.baseio.slackclone.root

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dev.baseio.slackclone.commonui.theme.AlphaNearOpaque
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.Route
import dev.baseio.slackclone.navigator.Screen
import dev.baseio.slackclone.uidashboard.nav.dashboardNavigation
import dev.baseio.slackclone.uionboarding.nav.onboardingNavigation
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var composeNavigator: ComposeNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    installSplashScreen()
    setContent {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        composeNavigator.handleNavigationCommands(navController)
      }

      ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        NavHost(
          navController = navController,
          startDestination = Route.OnBoarding.name,
        ) {
          onboardingNavigation(
            composeNavigator = composeNavigator,
          )
          dashboardNavigation(
            composeNavigator = composeNavigator
          )
        }
      }


    }
  }
}