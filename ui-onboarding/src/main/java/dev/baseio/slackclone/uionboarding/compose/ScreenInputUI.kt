package dev.baseio.slackclone.uionboarding.compose

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.uionboarding.R

@Composable
fun EmailAddressInputUI(composeNavigator: ComposeNavigator) {
  SlackCloneTheme() {
    CommonInputUI(
      composeNavigator,
      { modifier ->
        EmailInputView(modifier)
      },
      stringResource(id = R.string.subtitle_this_email_slack)
    )
  }
}

@Composable
fun WorkspaceInputUI(composeNavigator: ComposeNavigator) {
  SlackCloneTheme() {
    CommonInputUI(
      composeNavigator,
      {
        WorkspaceInputView(it)
      },
      stringResource(id = R.string.subtitle_this_address_slack)
    )
  }
}

