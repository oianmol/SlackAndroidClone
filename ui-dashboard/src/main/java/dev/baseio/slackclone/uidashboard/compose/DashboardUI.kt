package dev.baseio.slackclone.uidashboard.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.slackclone.commonui.theme.DarkBackground
import dev.baseio.slackclone.commonui.theme.SlackCloneColor
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme

@Composable
fun DashboardUI() {
  val scaffoldState = rememberScaffoldState()

  val sysUiController = rememberSystemUiController()

  LaunchedEffect(Unit) {
    sysUiController.setNavigationBarColor(color = SlackCloneColor)
    sysUiController.setSystemBarsColor(color = SlackCloneColor)
  }

  Scaffold(
    backgroundColor = SlackCloneTheme.colors.uiBackground,
    contentColor = SlackCloneTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    scaffoldState = scaffoldState,
    snackbarHost = {
      scaffoldState.snackbarHostState
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      SlackCloneSurface(
        color = SlackCloneTheme.colors.uiBackground,
        modifier = Modifier
      ) {
        Text("Dashboard")
      }
    }

  }
}