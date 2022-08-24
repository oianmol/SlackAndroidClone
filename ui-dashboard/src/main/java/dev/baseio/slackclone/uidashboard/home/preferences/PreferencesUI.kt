package dev.baseio.slackclone.uidashboard.home.preferences

import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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

@Composable
fun PreferencesUI(
  prefVM: PreferencesVM = hiltViewModel(),
  composeNavigator: ComposeNavigator
) {
  val scaffoldState = rememberScaffoldState()
  val itemClickStates: ItemClickStates = prefVM.itemClickStates
  val popUpItemStates: PopUpItemStates = prefVM.popUpItemStates

  val onPrefItemClick: (SlackPreferences) -> Unit =
    { pref -> handlePrefItemClicks(pref, itemClickStates) }

  val onSwitchStateChanged: (SlackPreferences, Boolean) -> Unit = { pref, updatedState ->
    handleSwitchItemClicks(pref, prefVM, updatedState)
  }

  HandlePrefItemClicks(itemClickStates, popUpItemStates, scaffoldState)

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
    ) { paddingValues ->
      SlackCloneSurface(
        color = SlackCloneColorProvider.colors.uiBackground,
        modifier = Modifier.padding(paddingValues).fillMaxSize()
      ) {
        LazyColumn(
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          items(prefVM.preferencesList) { prefItem ->
            PrefItemUI(
              onPrefItemClick = onPrefItemClick, prefItem = prefItem,
              onSwitchStateChange = onSwitchStateChanged
            )
          }
        }
      }
    }
  }
}

fun handleSwitchItemClicks(
  pref: SlackPreferences,
  prefVM: PreferencesVM,
  updatedState: Boolean
) {
  val switchStates = prefVM.switchesStates
  when (pref.id) {
    1 -> {
      switchStates.timeZone.value = updatedState
    }
    4 -> {
      switchStates.allowAnimation.value = updatedState
    }
    5 -> {
      switchStates.underlineLinks.value = updatedState
    }
    6 -> {
      switchStates.displayTypingIndicators.value = updatedState
    }
    7 -> {
      switchStates.openWebPageInSlack.value = updatedState
    }
    8 -> {
      switchStates.optimizeImageUploads.value = updatedState
    }
    9 -> {
      switchStates.optimizeVideoUploads.value = updatedState
    }
    14 -> {
      switchStates.showImagePreviews.value = updatedState
    }
  }
}

private fun handlePrefItemClicks(
  pref: SlackPreferences,
  itemClickStates: ItemClickStates
) {
  when (pref.id) {
    0 -> {
      itemClickStates.language.value = true
    }
    2 -> {
      itemClickStates.defaultEmoji.value = true
    }
    3 -> {
      itemClickStates.darkMode.value = true
    }
    11 -> {
      itemClickStates.resetCache.value = true
    }
    12 -> {
      itemClickStates.forceStop.value = true
    }
    13 -> {
      itemClickStates.sendFeeback.value = true
    }
    15 -> {
      itemClickStates.helpCenter.value = true
    }
    16 -> {
      itemClickStates.privacyPolicy.value = true
    }
    17 -> {
      itemClickStates.openSourceLicenses.value = true
    }
    18 -> {
      itemClickStates.version.value = true
    }
  }
}

@Composable
fun PrefItemUI(
  onSwitchStateChange: (SlackPreferences, Boolean) -> Unit,
  onPrefItemClick: (SlackPreferences) -> Unit,
  prefItem: SlackPreferences,
  prefVM: PreferencesVM = hiltViewModel()
) {
  val switchInitialState = prefVM.switchesStates
  Row(modifier = Modifier.fillMaxWidth()) {
    when (prefItem.id) {
      0 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          PrefHeading(text = "Time and place")
          ItemTypePopUp(
            prefItem, drawable.ic_outline_language_24, onPrefItemClick
          )
        }
      }
      1 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
            prefItem, drawable.ic_outline_hourglass_bottom_24, onSwitchStateChange, switchInitialState.timeZone.value
          )
        }
      }
      2 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Look and feel")
          ItemTypePopUp(
            prefItem, drawable.ic_baseline_front_hand_24, onPrefItemClick
          )
        }
      }
      3 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
            prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick
          )

        }
      }
      4 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Accessibility")
          ItemWithSlider(
            prefItem, drawable.ic_outline_broken_image_24, onSwitchStateChange, switchInitialState.allowAnimation.value
          )
        }
      }
      5 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
            prefItem, drawable.ic_outline_broken_image_24, onSwitchStateChange, switchInitialState.underlineLinks.value
          )

        }
      }
      6 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
            prefItem, drawable.ic_outline_keyboard_alt_24, onSwitchStateChange, switchInitialState.displayTypingIndicators.value
          )

        }
      }
      7 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Advanced")
          ItemWithSlider(
            prefItem, drawable.ic_outline_broken_image_24, onSwitchStateChange, switchInitialState.openWebPageInSlack.value
          )
        }
      }
      8 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
            prefItem, drawable.ic_outline_speed_24, onSwitchStateChange, switchInitialState.optimizeImageUploads.value
          )

        }
      }
      9 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
            prefItem, drawable.ic_outline_speed_24, onSwitchStateChange, switchInitialState.optimizeVideoUploads.value
          )

        }
      }
      10 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
            prefItem, drawable.ic_outline_image_24, onSwitchStateChange, switchInitialState.showImagePreviews.value
          )

        }
      }
      11 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "Troubleshooting")
          ItemTypePopUp(
            prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick
          )
        }
      }
      12 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
            prefItem, drawable.ic_outline_broken_image_24, onPrefItemClick
          )

        }
      }
      13 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
            prefItem, drawable.ic_outline_feedback_24, onPrefItemClick
          )
        }
      }
      14 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemWithSlider(
            prefItem, drawable.ic_outline_call_24, onSwitchStateChange, switchInitialState.callDebugging.value
          )
        }
      }
      15 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
            prefItem, drawable.ic_outline_help_outline_24, onPrefItemClick
          )
        }
      }
      16 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          SectionDivider()
          PrefHeading(text = "About Slack")
          ItemTypePopUp(
            prefItem, drawable.ic_outline_feed_24, onPrefItemClick
          )
        }
      }
      17 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
            prefItem, drawable.ic_outline_feed_24, onPrefItemClick
          )
        }
      }
      18 -> {
        Column(modifier = Modifier.fillMaxWidth()) {
          ItemTypePopUp(
            prefItem, drawable.ic_outline_info_24, onPrefItemClick
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
  LaunchedEffect(key1 = itemClickStates.version.value) {
    if(itemClickStates.version.value) {
      scaffoldState.snackbarHostState.showSnackbar("Version info copied!")
      itemClickStates.version.value = false
    }
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
    }
    )
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