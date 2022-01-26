package dev.baseio.slackclone.uidashboard.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.*

@Composable
fun DashboardUI() {
  val scaffoldState = rememberScaffoldState()

  val sysUiController = rememberSystemUiController()

  LaunchedEffect(Unit) {
    sysUiController.setSystemBarsColor(color = SlackCloneColor)
    sysUiController.setNavigationBarColor(color = Color.White)
  }

  Scaffold(
    backgroundColor = SlackCloneTheme.colors.uiBackground,
    contentColor = SlackCloneTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),

    scaffoldState = scaffoldState,
    topBar = {
      SlackSurfaceAppBar(
        title = {
          Text(text = "mutualmobile", style = SlackCloneTypography.h6.copy(color = Color.White))
        },
        navigationIcon = {
          MMImageButton()
        },
        backgroundColor = SlackCloneTheme.colors.uiBackground,
      )
    },
    snackbarHost = {
      scaffoldState.snackbarHostState
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      SlackCloneSurface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
      ) {
      }
    }

  }
}

@Composable
private fun MMImageButton() {
  IconButton(onClick = {

  }) {
    MMImage()
  }
}

@Composable
private fun MMImage() {
  Image(
    painter = rememberImagePainter(
      data = "https://avatars.slack-edge.com/2018-07-20/401750958992_1b07bb3c946bc863bfc6_88.png",
      builder = {
        transformations(RoundedCornersTransformation(8.0f,8.0f,8.0f,8.0f,))
      }
    ),
    contentDescription = null,
    modifier = Modifier.size(38.dp)
  )
}