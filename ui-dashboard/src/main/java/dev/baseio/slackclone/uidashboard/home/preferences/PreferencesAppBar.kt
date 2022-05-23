package dev.baseio.slackclone.uidashboard.home.preferences

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.uidashboard.R.drawable
import dev.baseio.slackclone.uidashboard.R.string

@Composable
fun PreferencesAppBar(composeNavigator: ComposeNavigator) {
  SlackSurfaceAppBar(
      title = {
        Text(
            text = stringResource(string.preferences),
            style = SlackCloneTypography.subtitle1.copy(
                color = SlackCloneColorProvider.colors.appBarTextTitleColor
            ), fontSize = 16.sp
        )
      },
      navigationIcon = {
        IconButton(
            onClick = {
              composeNavigator.navigateUp()
            }
        ) {
          Icon(
              painterResource(id = drawable.ic_baseline_close_24),
              contentDescription = "close preferences",
              tint = SlackCloneColorProvider.colors.buttonTextColor,
              modifier = Modifier.padding(start = 8.dp)
          )
        }
      },
      backgroundColor = SlackCloneColorProvider.colors.appBarColor,
  )
}
