package dev.baseio.slackclone.uionboarding

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import dev.baseio.slackclone.navigator.ComposeNavigator

@Composable
fun EmailAddressInputUI(composeNavigator: ComposeNavigator) {
  CommonInputUI(
    composeNavigator = composeNavigator,
    { modifier ->
      EmailInputView(modifier)
    }, stringResource(id = R.string.subtitle_this_email_slack)
  )
}

@Composable
fun WorkspaceInputUI(composeNavigator: ComposeNavigator) {
  CommonInputUI(
    composeNavigator = composeNavigator,
    {
      WorkspaceInputView(it)
    },
    stringResource(id = R.string.subtitle_this_address_slack)
  )
}

