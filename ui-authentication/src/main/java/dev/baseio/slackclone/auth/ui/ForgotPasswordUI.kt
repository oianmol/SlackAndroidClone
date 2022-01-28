package dev.baseio.slackclone.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.auth.R
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.auth.vm.ForgotPasswordVM
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator

@Composable
fun ForgotPasswordUI(
  forgotPasswordVM: ForgotPasswordVM = hiltViewModel(),
  composeNavigator: ComposeNavigator,
  navigatorFragment: FragmentNavGraphNavigator
) {
  Scaffold(
    backgroundColor = SlackCloneColorProvider.colors.uiBackground,
    contentColor = SlackCloneColorProvider.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      SlackSurfaceAppBar(title = {

      }, backgroundColor = SlackCloneColorProvider.colors.uiBackground)
    }) {
    ForgotPasswordSurface(forgotPasswordVM)
  }

}

@Composable
private fun ForgotPasswordSurface(forgotPasswordVM: ForgotPasswordVM) {
  SlackCloneSurface(
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth()
  ) {
    Column(
      Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .fillMaxHeight(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = R.mipmap.ic_launcher),
        contentDescription = "Logo", Modifier.size(128.dp)
      )

      EmailTF(forgotPasswordVM)

      ForgotPasswordButton(forgotPasswordVM)

    }
  }
}

@Composable
private fun ForgotPasswordButton(forgotPasswordVM: ForgotPasswordVM) {
  Button(
    onClick = {
      forgotPasswordVM.navigateBack()
    }, Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = SlackCloneColorProvider.colors.buttonColor)
  ) {
    Text(
      text = "Reset Password",
      style = SlackCloneTypography.body1.copy(color = SlackCloneColorProvider.colors.buttonTextColor)
    )
  }
}

@Composable
private fun EmailTF(forgotPasswordVM: ForgotPasswordVM) {
  TextField(
    value = forgotPasswordVM.email.value, onValueChange = {
      forgotPasswordVM.email.value = it
    },
    Modifier
      .padding(16.dp)
      .fillMaxWidth(), label = {
      Text(
        text = "Email",
        style = SlackCloneTypography.body2.copy(color = SlackCloneColorProvider.colors.textPrimary)
      )
    },
    shape = SlackCloneShapes.large,
    keyboardOptions = KeyboardOptions(
      imeAction = ImeAction.Done, keyboardType = KeyboardType.Email
    ),
    leadingIcon = {
      Image(
        painter = painterResource(id = R.drawable.ic_email),
        contentDescription = "Email"
      )
    },
    colors = textFieldColors(),
    maxLines = 1,
    singleLine = true
  )
}

@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
  focusedIndicatorColor = Color.Transparent,
  disabledIndicatorColor = Color.Transparent,
  unfocusedIndicatorColor = Color.Transparent,
  backgroundColor = Color.White,
)