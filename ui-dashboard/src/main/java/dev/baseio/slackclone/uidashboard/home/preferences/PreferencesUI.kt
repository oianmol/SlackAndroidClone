package dev.baseio.slackclone.uidashboard.home.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.data.local.model.SlackPreferences
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.uidashboard.R.drawable
import dev.baseio.slackclone.uidashboard.home.preferences.prefitems.ItemTypePopUp
import dev.baseio.slackclone.uidashboard.home.preferences.prefitems.ItemWithSlider

@Composable
fun PreferencesUI(
  onPrefItemClick: (SlackPreferences) -> Unit = {},
  prefVM: PreferencesVM = hiltViewModel(),
  composeNavigator: ComposeNavigator
) {
  val scaffoldState = rememberScaffoldState()
  SlackCloneTheme {
    Scaffold(
        backgroundColor = SlackCloneColorProvider.colors.uiBackground,
        contentColor = SlackCloneColorProvider.colors.textSecondary,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        scaffoldState = scaffoldState,
        topBar = { PreferencesAppBar(composeNavigator) }
    ) {
      SlackCloneSurface(
          color = SlackCloneColorProvider.colors.uiBackground,
          modifier = Modifier.fillMaxSize()
      ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          items(prefVM.preferencesList) { prefItem ->
            PrefItemUI(onPrefItemClick, prefItem)
          }
        }
      }
    }
  }
}

@Composable
fun PrefItemUI(
  onPrefItemClick: (SlackPreferences) -> Unit,
  prefItem: SlackPreferences
) {
  Row(modifier = Modifier.fillMaxWidth()) {
    when (prefItem.id) {
      0 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          PrefHeading(text = "Time and place")
          ItemTypePopUp(prefItem, drawable.ic_outline_language_24, onPrefItemClick)
        }
      }
      1 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(prefItem, drawable.ic_outline_hourglass_bottom_24, sliderStateIndex = 0)
        }
      }
      2 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Look and feel")
          ItemTypePopUp(prefItem, drawable.ic_baseline_front_hand_24, onPrefItemClick)
        }
      }
      3 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick)

        }
      }
      4 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Accessibility")
          ItemWithSlider(prefItem, drawable.ic_outline_broken_image_24, sliderStateIndex = 1)
        }
      }
      5 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(prefItem, drawable.ic_outline_broken_image_24, sliderStateIndex = 2)

        }
      }
      6 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(prefItem, drawable.ic_outline_keyboard_alt_24, sliderStateIndex = 3)

        }
      }
      7 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Advanced")
          ItemWithSlider(prefItem, drawable.ic_outline_broken_image_24, sliderStateIndex = 4)
        }
      }
      8 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(prefItem, drawable.ic_outline_speed_24, sliderStateIndex = 5)

        }
      }
      9 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(prefItem, drawable.ic_outline_speed_24, sliderStateIndex = 6)

        }
      }
      10 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(prefItem, drawable.ic_outline_image_24, sliderStateIndex = 7)

        }
      }
      11 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Troubleshooting")
          ItemTypePopUp(prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick)
        }
      }
      12 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick)

        }
      }
      13 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(prefItem, drawable.ic_outline_feedback_24, onPrefItemClick)
        }
      }
      14 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(prefItem, drawable.ic_outline_call_24, sliderStateIndex = 8)
        }
      }
      15 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(prefItem, drawable.ic_outline_help_outline_24, onPrefItemClick)
        }
      }
      16 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "About Slack")
          ItemTypePopUp(prefItem, drawable.ic_outline_feed_24, onPrefItemClick)
        }
      }
      17 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(prefItem, drawable.ic_outline_feed_24, onPrefItemClick)
        }
      }
      18 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(prefItem, drawable.ic_outline_info_24, onPrefItemClick)
        }
      }
    }
  }
}

@Composable
private fun SectionDivider() {
  Divider(
      color = SlackCloneColorProvider.colors.buttonColor.copy(0.7F),
      modifier = Modifier.padding(bottom = 16.dp)
  )
}

@Composable
fun PrefHeading(text: String) {
  Text(
      modifier = Modifier.padding(bottom = 16.dp),
      text = text, style = SlackCloneTypography.body1.copy(
      color = SlackCloneColorProvider.colors.textPrimary,
      fontSize = 14.sp
  ), fontWeight = FontWeight.Bold
  )
}