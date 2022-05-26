package dev.baseio.slackclone.uidashboard.home.preferences.uicomponents

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import dev.baseio.slackclone.commonui.theme.DarkBlue
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@Composable
fun DoubleOptionDialog(
  confirmButtonText: String,
  cancelButtonText: String,
  title: String,
  messsage: String,
  onDismiss: () -> Unit = {},
  onConfirm: () -> Unit = {},
) {
  SlackCloneTheme {
    AlertDialog(
        backgroundColor = SlackCloneColorProvider.colors.uiBackground,
        onDismissRequest = {
        }, title = {
      Text(
          text = title,
          style = SlackCloneTypography.body1.copy(
              color = SlackCloneColorProvider.colors.textPrimary,
          )
      )
    }, confirmButton = {
      TextButton(onClick = { onConfirm() }) {
        Text(
            confirmButtonText, style = SlackCloneTypography.body1.copy(
            color = DarkBlue
        )
        )
      }
    }, dismissButton = {
      TextButton(onClick = { onDismiss() }) {
        Text(
            text = cancelButtonText, style = SlackCloneTypography.body1.copy(
            color = DarkBlue
        )
        )
      }
    }, text = {
      Text(
          text = messsage, style = SlackCloneTypography.body1.copy(
          color = SlackCloneColorProvider.colors.textPrimary
      )
      )
    }
    )
  }
}