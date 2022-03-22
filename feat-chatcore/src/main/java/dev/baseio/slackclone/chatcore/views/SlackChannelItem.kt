package dev.baseio.slackclone.chatcore.views

import android.text.format.DateUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.commonui.reusable.SlackListItem
import dev.baseio.slackclone.commonui.reusable.SlackOnlineBox
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SlackChannelItem(
  slackChannel: UiLayerChannels.SlackChannel,
  textColor: Color = SlackCloneColorProvider.colors.textPrimary,
  onItemClick: (UiLayerChannels.SlackChannel) -> Unit
) {
  when (slackChannel.isOneToOne) {
    true -> {
      DirectMessageChannel(onItemClick, slackChannel, textColor)
    }
    else -> {
      GroupChannelItem(slackChannel, onItemClick)
    }
  }
}

@Composable
private fun GroupChannelItem(
  slackChannel: UiLayerChannels.SlackChannel,
  onItemClick: (UiLayerChannels.SlackChannel) -> Unit
) {
  SlackListItem(
    icon = if (slackChannel.isPrivate == true) Icons.Default.Lock else Icons.Default.MailOutline,
    title = "${slackChannel.name}",
    onItemClick = {
      onItemClick(slackChannel)
    }
  )
}

@Composable
private fun DirectMessageChannel(
  onItemClick: (UiLayerChannels.SlackChannel) -> Unit,
  slackChannel: UiLayerChannels.SlackChannel,
  textColor: Color
) {
  Row(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxWidth()
      .clickable {
        onItemClick(slackChannel)
      }, verticalAlignment = Alignment.CenterVertically
  ) {
    SlackOnlineBox(imageUrl = slackChannel.pictureUrl ?: "")
    ChannelText(slackChannel, textColor)
  }
}

@Composable
fun DMLastMessageItem(
  onItemClick: (UiLayerChannels.SlackChannel) -> Unit,
  slackChannel: UiLayerChannels.SlackChannel,
  slackMessage: DomainLayerMessages.SlackMessage,
) {
  Row(
    modifier = Modifier
      .padding(start = 8.dp, bottom = 4.dp)
      .fillMaxWidth()
      .clickable {
        onItemClick(slackChannel)
      }, verticalAlignment = Alignment.CenterVertically
  ) {

    SlackListItem(icon = {
      SlackOnlineBox(
        imageUrl = slackChannel.pictureUrl ?: "",
        parentModifier = Modifier.size(48.dp),
        imageModifier = Modifier.size(40.dp)
      )
    }, center = {
      Column(it) {
        ChannelText(slackChannel, SlackCloneColorProvider.colors.textPrimary)
        ChannelMessage(slackMessage, SlackCloneColorProvider.colors.textSecondary)
      }
    }, trailingItem = {
      RelativeTime(slackMessage.createdDate)
    }, onItemClick = {
      onItemClick(slackChannel)
    })

  }
}


@Composable
private fun ChannelMessage(slackMessage: DomainLayerMessages.SlackMessage, textSecondary: Color) {
  Text(
    text = slackMessage.message,
    style = SlackCloneTypography.subtitle1.copy(
      color = textSecondary.copy(
        alpha = 0.8f
      ),
    ), modifier = Modifier
      .padding(start = 8.dp, top = 4.dp),
    maxLines = 2,
    overflow = TextOverflow.Ellipsis
  )
}

@Composable
fun RelativeTime(createdDate: Long) {
  Text(
    DateUtils.getRelativeTimeSpanString(
      createdDate,
      System.currentTimeMillis(),
      0L,
      DateUtils.FORMAT_ABBREV_RELATIVE
    ).toString(),
    style = SlackCloneTypography.caption.copy(
      color = SlackCloneColorProvider.colors.textSecondary
    ), modifier = Modifier.padding(4.dp)
  )
}

@Composable
private fun ChannelText(
  slackChannel: UiLayerChannels.SlackChannel,
  textColor: Color
) {
  Text(
    text = "${slackChannel.name}",
    style = SlackCloneTypography.subtitle1.copy(
      color = textColor.copy(
        alpha = 0.8f
      )
    ), modifier = Modifier
      .padding(8.dp), maxLines = 1,
    overflow = TextOverflow.Ellipsis
  )
}