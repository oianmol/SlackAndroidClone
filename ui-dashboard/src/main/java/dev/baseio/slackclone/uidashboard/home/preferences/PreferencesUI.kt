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
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.DoubleOptionDialog
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.EmojiSkinColorDialog
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.ItemClickStates
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.PopUpItemStates
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.RadioButtonPickerDialog
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.SwitchesStates
import kotlinx.coroutines.launch

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
        topBar = { PreferencesAppBar(composeNavigator) },
        snackbarHost = {
          SnackbarHost(hostState = it) { data ->
            Snackbar(
                backgroundColor = SlackCloneColorProvider.colors.uiBackground,
                contentColor = SlackCloneColorProvider.colors.textPrimary, snackbarData = data
            )
          }
        }
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
            PrefItemUI(
                onPrefItemClick = onPrefItemClick, prefItem = prefItem,
                scaffoldState = scaffoldState
            )
          }
        }
      }
    }
  }
}

@Composable
fun PrefItemUI(
  prefVM: PreferencesVM = hiltViewModel(),
  onPrefItemClick: (SlackPreferences) -> Unit,
  prefItem: SlackPreferences,
  scaffoldState: ScaffoldState
) {
  val itemClickStates: ItemClickStates = prefVM.itemClickStates
  val popUpItemStates: PopUpItemStates = prefVM.popUpItemStates
  val switchStates: SwitchesStates = prefVM.switchesStates
  HandlePrefItemClicks(itemClickStates, popUpItemStates, scaffoldState)

  Row(modifier = Modifier.fillMaxWidth()) {
    when (prefItem.id) {
      0 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          PrefHeading(text = "Time and place")
          ItemTypePopUp(
              prefItem, drawable.ic_outline_language_24, onPrefItemClick, itemClickStates.language
          )
        }
      }
      1 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
              prefItem, drawable.ic_outline_hourglass_bottom_24, sliderState = switchStates.timeZone
          )
        }
      }
      2 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Look and feel")
          ItemTypePopUp(
              prefItem, drawable.ic_baseline_front_hand_24, onPrefItemClick,
              itemClickStates.defaultEmoji
          )
        }
      }
      3 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
              prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick,
              itemClickStates.darkMode
          )

        }
      }
      4 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Accessibility")
          ItemWithSlider(
              prefItem, drawable.ic_outline_broken_image_24,
              sliderState = switchStates.allowAnimation
          )
        }
      }
      5 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
              prefItem, drawable.ic_outline_broken_image_24,
              sliderState = switchStates.underlineLinks
          )

        }
      }
      6 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
              prefItem, drawable.ic_outline_keyboard_alt_24,
              sliderState = switchStates.displayTypingIndicators
          )

        }
      }
      7 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Advanced")
          ItemWithSlider(
              prefItem, drawable.ic_outline_broken_image_24,
              sliderState = switchStates.openWebPageInSlack
          )
        }
      }
      8 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
              prefItem, drawable.ic_outline_speed_24,
              sliderState = switchStates.optimizeImageUploads
          )

        }
      }
      9 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
              prefItem, drawable.ic_outline_speed_24,
              sliderState = switchStates.optimizeVideoUploads
          )

        }
      }
      10 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
              prefItem, drawable.ic_outline_image_24, sliderState = switchStates.showImagePreviews
          )

        }
      }
      11 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Troubleshooting")
          ItemTypePopUp(
              prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick,
              itemClickStates.resetCache
          )
        }
      }
      12 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
              prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick,
              itemClickStates.forceStop
          )

        }
      }
      13 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
              prefItem, drawable.ic_outline_feedback_24, onPrefItemClick, itemClickStates.language
          )
        }
      }
      14 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
              prefItem, drawable.ic_outline_call_24, sliderState = switchStates.callDebugging
          )
        }
      }
      15 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
              prefItem, drawable.ic_outline_help_outline_24, onPrefItemClick,
              itemClickStates.language
          )
        }
      }
      16 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "About Slack")
          ItemTypePopUp(
              prefItem, drawable.ic_outline_feed_24, onPrefItemClick, itemClickStates.language
          )
        }
      }
      17 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
              prefItem, drawable.ic_outline_feed_24, onPrefItemClick, itemClickStates.language
          )
        }
      }
      18 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
              prefItem, drawable.ic_outline_info_24, onPrefItemClick, itemClickStates.version
          )
        }
      }
    }
  }
}

@Composable
private fun HandlePrefItemClicks(
  itemClickStates: ItemClickStates,
  popUpItemStates: PopUpItemStates,
  scaffoldState: ScaffoldState
) {
  HandleDefaultEmojiClick(itemClickStates, popUpItemStates)

  HandleLanguageClick(itemClickStates)

  HandleResetCacheClick(itemClickStates, popUpItemStates)

  HandleDarkModeClick(itemClickStates, popUpItemStates)

  HandleForcestopClick(itemClickStates, popUpItemStates)

  HandleVersionInfoClick(itemClickStates, scaffoldState = scaffoldState)
}

@Composable
private fun HandleVersionInfoClick(
  itemClickStates: ItemClickStates,
  scaffoldState: ScaffoldState
) {
  val scope = rememberCoroutineScope()

  if (itemClickStates.version.value) {
    scope.launch {
      scaffoldState.snackbarHostState.showSnackbar("Version info copied!")
    }
    itemClickStates.version.value = false
  }
}

@Composable
private fun HandleForcestopClick(
  itemClickStates: ItemClickStates,
  popUpItemStates: PopUpItemStates
) {
  if (itemClickStates.forceStop.value) {
    DoubleOptionDialog(
        confirmButtonText = "Force stop", cancelButtonText = "Cancel",
        title = "Unsaved data may be lost",
        messsage = "Are you sure you want to force Slack to stop running?",
        onConfirm = {
          itemClickStates.forceStop.value = false
          popUpItemStates.forceStopState.value = "Cancel"
        },
        onDismiss = {
          itemClickStates.forceStop.value = false
          popUpItemStates.forceStopState.value = "Force stop"
        }
    )
  }
}

@Composable
private fun HandleDarkModeClick(
  itemClickStates: ItemClickStates,
  popUpItemStates: PopUpItemStates
) {
  if (itemClickStates.darkMode.value) {
    RadioButtonPickerDialog(options = listOf("System default", "Off", "On"),
        popUpItemStates.darkMode,
        onDismiss = {
          itemClickStates.darkMode.value = false
        }, onConfirm = {
      itemClickStates.darkMode.value = false
    })
  }
}

@Composable
private fun HandleResetCacheClick(
  itemClickStates: ItemClickStates,
  popUpItemStates: PopUpItemStates
) {
  if (itemClickStates.resetCache.value) {
    DoubleOptionDialog(
        confirmButtonText = "Yes", cancelButtonText = "No", title = "Reset cache",
        messsage = "Are you sure you would like to reset cache?", onConfirm = {
      itemClickStates.resetCache.value = false
      popUpItemStates.resetCache.value = "Yes"
    }, onDismiss = {
      itemClickStates.resetCache.value = false
      popUpItemStates.resetCache.value = "No"
    }
    )
  }
}

@Composable
private fun HandleLanguageClick(itemClickStates: ItemClickStates) {
  if (itemClickStates.language.value) {
    RadioButtonPickerDialog(options = listOf("English", "Spanish"), onDismiss = {
      itemClickStates.language.value = false
    }, onConfirm = {
      itemClickStates.language.value = false
    })
  }
}

@Composable
private fun HandleDefaultEmojiClick(
  itemClickStates: ItemClickStates,
  popUpItemStates: PopUpItemStates
) {
  if (itemClickStates.defaultEmoji.value) {
    EmojiSkinColorDialog(emojisList = listOf("light", "dark"), popUpItemStates.emojiSkinColor,
        onDismiss = {
          itemClickStates.defaultEmoji.value = false
        },
        onConfirm = {
          itemClickStates.defaultEmoji.value = false
        })
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