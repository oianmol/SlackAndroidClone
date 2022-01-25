package com.praxis.feat.authentication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.theme.AlphaNearTransparent
import com.mutualmobile.praxis.commonui.theme.PraxisShapes
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.praxis.feat.authentication.R
import com.praxis.feat.authentication.vm.ForgotPasswordVM

@Composable
fun ForgotPasswordUI(forgotPasswordVM: ForgotPasswordVM = hiltViewModel()){
  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      CommonTopAppBar(titleText = "ForgotPasswordentication")
    }) {
    ForgotPasswordSurface(forgotPasswordVM)
  }

}

@Composable
private fun ForgotPasswordSurface(forgotPasswordVM: ForgotPasswordVM) {
  PraxisSurface(
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
    colors = ButtonDefaults.buttonColors(backgroundColor = PraxisTheme.colors.buttonColor)
  ) {
    Text(
      text = "Reset Password",
      style = MaterialTheme.typography.body1.copy(color = PraxisTheme.colors.buttonTextColor)
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
        style = MaterialTheme.typography.body2.copy(color = PraxisTheme.colors.textPrimary)
      )
    },
    shape = PraxisShapes.large,
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
  backgroundColor = PraxisTheme.colors.accent.copy(alpha = AlphaNearTransparent),
)