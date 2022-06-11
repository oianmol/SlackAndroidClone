package dev.baseio.slackclone.uidashboard.home.preferences.uicomponents

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class SwitchesStates(
  var timeZone: MutableState<Boolean> = mutableStateOf(false),
  var allowAnimation: MutableState<Boolean> = mutableStateOf(false),
  var underlineLinks: MutableState<Boolean> = mutableStateOf(false),
  var displayTypingIndicators: MutableState<Boolean> = mutableStateOf(false),
  var openWebPageInSlack: MutableState<Boolean> = mutableStateOf(false),
  var optimizeImageUploads: MutableState<Boolean> = mutableStateOf(false),
  var optimizeVideoUploads: MutableState<Boolean> = mutableStateOf(false),
  var showImagePreviews: MutableState<Boolean> = mutableStateOf(false),
  var callDebugging: MutableState<Boolean> = mutableStateOf(false)
)

class ItemClickStates(
  var language: MutableState<Boolean> = mutableStateOf(false),
  var defaultEmoji: MutableState<Boolean> = mutableStateOf(false),
  var darkMode: MutableState<Boolean> = mutableStateOf(false),
  var resetCache: MutableState<Boolean> = mutableStateOf(false),
  var forceStop: MutableState<Boolean> = mutableStateOf(false),
  var sendFeeback: MutableState<Boolean> = mutableStateOf(false),
  var helpCenter: MutableState<Boolean> = mutableStateOf(false),
  var privacyPolicy: MutableState<Boolean> = mutableStateOf(false),
  var openSourceLicenses: MutableState<Boolean> = mutableStateOf(false),
  var version: MutableState<Boolean> = mutableStateOf(false)
)