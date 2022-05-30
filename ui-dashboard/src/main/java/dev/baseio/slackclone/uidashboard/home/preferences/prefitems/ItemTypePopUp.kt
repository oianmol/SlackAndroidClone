package dev.baseio.slackclone.uidashboard.home.preferences.prefitems

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.data.local.model.SlackPreferences

@Composable fun ItemTypePopUp(
  prefItem: SlackPreferences,
  @DrawableRes icon: Int,
  onOptionsClick: (SlackPreferences) -> Unit = {},
) {
  Row(
      modifier = Modifier
          .fillMaxWidth()
          .clickable(interactionSource = MutableInteractionSource(), indication = rememberRipple(),
              onClick = {
                onOptionsClick(prefItem)
              })
  ) {
    IconButton(onClick = {
    }) {
      Icon(painter = painterResource(id = icon), contentDescription = prefItem.description)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
      Text(
          modifier = Modifier.padding(bottom = 8.dp),
          text = prefItem.name, style = SlackCloneTypography.body1.copy(
          color = SlackCloneColorProvider.colors.textPrimary
      )
      )
      Text(
          text = prefItem.value, maxLines = 3,
          style = SlackCloneTypography.body1.copy(
              color = SlackCloneColorProvider.colors.textPrimary,
              fontSize = 14.sp,
          )
      )
    }
  }
}
