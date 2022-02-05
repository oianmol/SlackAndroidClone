package dev.baseio.slackclone.chatcore.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.commonui.reusable.SlackImageBox
import dev.baseio.slackclone.commonui.reusable.SlackListItem
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SlackChannelItem(
  slackChannel: UiLayerChannels.SlackChannel,
  onItemClick: (UiLayerChannels.SlackChannel) -> Unit
) {
  if (slackChannel.isOneToOne == true) {
    Row(
      modifier = Modifier
        .padding(8.dp)
        .clickable {
          onItemClick(slackChannel)
        }, verticalAlignment = Alignment.CenterVertically
    ) {
      SlackImageBox(modifier = Modifier.size(24.dp), imageUrl = slackChannel.pictureUrl ?: "")
      Text(
        text = "${slackChannel.name}",
        style = SlackCloneTypography.subtitle1.copy(
          color = SlackCloneColorProvider.colors.textPrimary.copy(
            alpha = 0.8f
          )
        ), modifier = Modifier
          .padding(8.dp)
      )
    }
  } else {
    SlackListItem(
      icon = if (slackChannel.isPrivate == true) Icons.Default.Lock else Icons.Default.MailOutline,
      title = "${slackChannel.name}",
      onItemClick = {
        onItemClick(slackChannel)
      }
    )
  }
}