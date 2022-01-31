package dev.baseio.slackclone.uionboarding.compose

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator
import dev.baseio.slackclone.uionboarding.R

@Composable
fun EmailAddressInputUI(
  fragmentNavigator: FragmentNavGraphNavigator
) {
  CommonInputUI(
    fragmentNavigator = fragmentNavigator,
    { modifier ->
      EmailInputView(modifier)
    },
    stringResource(id = R.string.subtitle_this_email_slack)
  )
}

@Composable
fun WorkspaceInputUI(
  fragmentNavigator: FragmentNavGraphNavigator
) {
  CommonInputUI(
    fragmentNavigator = fragmentNavigator,
    {
      WorkspaceInputView(it)
    },
    stringResource(id = R.string.subtitle_this_address_slack)
  )
}

