package dev.baseio.slackclone.uidashboard.home.preferences.prefitems

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.slackclone.commonui.theme.DarkBlue
import dev.baseio.slackclone.commonui.theme.DisabledSwitchThumbColor
import dev.baseio.slackclone.commonui.theme.DisabledSwitchTrackColor
import dev.baseio.slackclone.commonui.theme.LightBlue
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.data.local.model.SlackPreferences

@Composable fun ItemWithSlider(
  prefItem: SlackPreferences,
  @DrawableRes icon: Int,
  onSliderStateChange: (SlackPreferences, Boolean) -> Unit,
  initialState: Boolean = false
) {
  val sliderState = remember { mutableStateOf(initialState) }
  Row(modifier = Modifier.fillMaxWidth()) {
    IconButton(onClick = { /*TODO*/ }) {
      Icon(painter = painterResource(id = icon), contentDescription = prefItem.description)
    }
    Column(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
          .fillMaxWidth()
      ) {
        Column(modifier = Modifier.fillMaxWidth(0.7F)) {
          Text(
            modifier = Modifier.padding(bottom = 8.dp), maxLines = 3,
            text = prefItem.name, style = SlackCloneTypography.body1.copy(
            color = SlackCloneColorProvider.colors.textPrimary
          )
          )
          Text(
            text = prefItem.value, maxLines = 3, style = SlackCloneTypography.body1.copy(
            color = SlackCloneColorProvider.colors.textPrimary,
            fontSize = 14.sp
          )
          )
        }
        Switch(
          checked = sliderState.value,
          onCheckedChange = {
            sliderState.value = it
            onSliderStateChange(prefItem, it)
          },
          colors = SwitchDefaults.colors(
            checkedThumbColor = DarkBlue,
            checkedTrackColor = LightBlue,
            uncheckedThumbColor = DisabledSwitchThumbColor,
            uncheckedTrackColor = DisabledSwitchTrackColor
          ),
          modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
      }
    }
  }
}