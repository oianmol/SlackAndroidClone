package dev.baseio.slackclone.uichat.chatthread.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.common.extensions.calendar
import dev.baseio.slackclone.common.extensions.formattedTime
import dev.baseio.slackclone.commonui.reusable.SlackImageBox
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatMessage(message: DomainLayerMessages.SlackMessage) {
  ListItem(icon = {
    SlackImageBox(Modifier.size(48.dp), imageUrl = "http://placekitten.com/200/300")
  }, modifier = Modifier.padding(2.dp), secondaryText = {
    ChatMedia(message)
  }, text = {
    ChatUserDateTime(message)
  })
}

@Composable
fun ChatMedia(message: DomainLayerMessages.SlackMessage) {
  Column {
    Text(
      message.message,
      style = SlackCloneTypography.subtitle2.copy(
        color = SlackCloneColorProvider.colors.textSecondary
      ), modifier = Modifier.padding(4.dp)
    )
  }

}

@Composable
fun ChatUserDateTime(message: DomainLayerMessages.SlackMessage) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Text(
      message.createdBy + " \uD83C\uDF34",
      style = SlackCloneTypography.subtitle1.copy(
        fontWeight = FontWeight.Bold,
        color = SlackCloneColorProvider.colors.textPrimary
      ), modifier = Modifier.padding(4.dp)
    )
    Text(
      message.createdDate.calendar().formattedTime(),
      style = SlackCloneTypography.overline.copy(
        color = SlackCloneColorProvider.colors.textSecondary.copy(alpha = 0.8f)
      ), modifier = Modifier.padding(4.dp)
    )
  }
}


