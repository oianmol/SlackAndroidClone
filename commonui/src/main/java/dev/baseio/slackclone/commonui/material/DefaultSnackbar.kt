package dev.baseio.slackclone.commonui.material

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@Composable
fun DefaultSnackbar(
  snackbarHostState: SnackbarHostState,
  modifier: Modifier = Modifier,
  onDismiss: () -> Unit = { }
) {
  SnackbarHost(
    hostState = snackbarHostState,
    snackbar = { data ->
      Snackbar(
        content = {
          Text(
            text = data.message,
            style = SlackCloneTypography.body1,
            color = SlackCloneTheme.colors.textPrimary,
            )
        },
        action = {
          data.actionLabel?.let { actionLabel ->
            TextButton(onClick = onDismiss) {
              Text(
                text = actionLabel,
                color = SlackCloneTheme.colors.textPrimary,
                style = SlackCloneTypography.body2
              )
            }
          }
        },
        backgroundColor = SlackCloneTheme.colors.accent
      )
    },
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight(Alignment.Bottom)
  )
}