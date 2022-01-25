package com.praxis.feat.authentication.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.NavigationKeys
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.Screen
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