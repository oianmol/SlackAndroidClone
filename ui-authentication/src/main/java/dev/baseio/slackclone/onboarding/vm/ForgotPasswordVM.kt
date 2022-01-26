package dev.baseio.slackclone.onboarding.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.NavigationKeys
import dev.baseio.slackclone.navigator.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordVM @Inject constructor(private val navigator: ComposeNavigator) : ViewModel() {
  var email = mutableStateOf("")

  fun navigateBack() {
    navigator.navigateBackWithResult(
      NavigationKeys.ForgotPassword, "Reset Password Done!",
      Screen.Auth.route
    )
  }
}