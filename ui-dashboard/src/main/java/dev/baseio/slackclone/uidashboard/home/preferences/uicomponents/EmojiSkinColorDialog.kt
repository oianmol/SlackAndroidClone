package dev.baseio.slackclone.uidashboard.home.preferences.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uidashboard.R

@Composable
fun EmojiSkinColorDialog(
  emojisList: List<String>,
  selectedEmoji: MutableState<String>,
  onDismiss: () -> Unit = {},
  onConfirm: () -> Unit = {},
) {
  SlackCloneTheme {
    AlertDialog(
        backgroundColor = SlackCloneColorProvider.colors.uiBackground,
        onDismissRequest = {
          onDismiss()
        }, title = {
      Text(
          text = "Default skin tone", style = SlackCloneTypography.body1.copy(
          color = SlackCloneColorProvider.colors.textPrimary
      )
      )
    }, text = {
      Column(
          modifier = Modifier
              .fillMaxWidth()
              .background(
                  color = SlackCloneColorProvider.colors.uiBackground
              ),
          verticalArrangement = Arrangement.SpaceBetween
      ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
          repeat(3) {
            Image(
                painterResource(id = R.drawable.ic_baseline_front_hand_24),
                contentDescription = "hand skin color"
            )
          }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
          repeat(3) {
            Image(
                painterResource(id = R.drawable.ic_baseline_front_hand_24),
                contentDescription = "hand skin color"
            )
          }
        }
      }
    }, buttons = {

    }, properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true
    )
    )
  }
}