package com.praxis.feat.authentication.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.material.DefaultSnackbar
import com.mutualmobile.praxis.commonui.theme.AlphaNearTransparent
import com.mutualmobile.praxis.commonui.theme.PraxisShapes
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.praxis.feat.authentication.R
import com.praxis.feat.authentication.vm.AuthVM

@Composable
fun AuthenticationUI(
  authVM: AuthVM = hiltViewModel()
) {
  val scaffoldState = rememberScaffoldState()
  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      CommonTopAppBar(titleText = "Authentication")
    }, scaffoldState = scaffoldState, snackbarHost = {
      scaffoldState.snackbarHostState
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      AuthSurface(
        authVM = authVM, scaffoldState = scaffoldState
      )
      DefaultSnackbar(scaffoldState.snackbarHostState) {
        authVM.snackBarState.value = ""
        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
      }
    }

  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun AuthSurface(
  authVM: AuthVM,
  scaffoldState: ScaffoldState
) {
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

      val resetPasswordState by authVM.snackBarState.collectAsState()
      Image(
        painter = painterResource(id = R.mipmap.ic_launcher),
        contentDescription = "Logo", Modifier.size(128.dp)
      )
      val formVisible by authVM.uiState.collectAsState()
      val (focusRequester) = FocusRequester.createRefs()

      AnimatedVisibility(visible = formVisible is AuthVM.UiState.Empty) {
        EmailTF(authVM,focusRequester)
      }

      AnimatedVisibility(visible = formVisible is AuthVM.UiState.Empty) {
        PasswordTF(authVM, focusRequester)
      }

      AnimatedVisibility(visible = (formVisible is AuthVM.UiState.LoadingState)) {
        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
      }

      AnimatedVisibility(visible = formVisible is AuthVM.UiState.Empty){
        LoginButton(authVM = authVM)
      }

      AnimatedVisibility(visible = formVisible is AuthVM.UiState.Empty) {
        ForgotPasswordText(authVM)
      }

      if (resetPasswordState.isNotEmpty()) {
        LaunchedEffect(scaffoldState) {
          scaffoldState.snackbarHostState.showSnackbar(
            message = resetPasswordState,
            actionLabel = "Ok"
          )
        }
      }
    }
  }
}

@Composable
fun ForgotPasswordText(authVM: AuthVM) {
  ClickableText(text = buildAnnotatedString {

    withStyle(
      style = SpanStyle(
        color = PraxisTheme.colors.accent,
      )
    ) {
      append("Forgot Password? ")
    }

  }, onClick = {
    authVM.navigateForgotPassword()
  }, modifier = Modifier.padding(8.dp))
}

@Composable
private fun LoginButton(
  authVM: AuthVM,
) {
  Button(
    onClick = {
      authVM.loginNow()
    }, Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = PraxisTheme.colors.buttonColor)
  ) {
    Text(
      text = "Login",
      style = MaterialTheme.typography.body1.copy(color = PraxisTheme.colors.buttonTextColor)
    )
  }
}

@ExperimentalComposeUiApi
@Composable
private fun PasswordTF(authVM: AuthVM, focusRequester: FocusRequester) {
  val credentials by authVM.credentials.collectAsState()
  val keyboardController = LocalSoftwareKeyboardController.current

  TextField(
    value = credentials.password ?: "",
    onValueChange = {
      authVM.credentials.value = credentials.copy(password = it)
    },
    modifier = Modifier
      .padding(16.dp)
      .focusRequester(focusRequester)
      .fillMaxWidth(),
    label = {
      Text(
        text = "Password",
        style = MaterialTheme.typography.body2.copy(color = PraxisTheme.colors.textPrimary)
      )
    },
    shape = PraxisShapes.large,
    keyboardActions = KeyboardActions(
      onDone = { keyboardController?.hide() }),
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    leadingIcon = {
      Image(
        painter = painterResource(id = R.drawable.ic_eye),
        contentDescription = "email"
      )
    },
    colors = textFieldColors(),
    maxLines = 1,
    singleLine = true,
    visualTransformation = PasswordVisualTransformation()
  )
}

@ExperimentalComposeUiApi
@Composable
private fun EmailTF(authVM: AuthVM,focusRequester: FocusRequester) {
  val credentials by authVM.credentials.collectAsState()

  TextField(
    value = credentials.email ?: "",
    onValueChange = {
      authVM.credentials.value = credentials.copy(email = it)
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
    keyboardOptions = KeyboardOptions(
      imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
    ),
    keyboardActions = KeyboardActions(
      onNext = {
        focusRequester.requestFocus()
      },
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
  backgroundColor = PraxisTheme.colors.accent.copy(alpha = AlphaNearTransparent),
)

@Preview("Light+Dark")
@Composable
fun PreviewAuth() {
  PraxisTheme(darkTheme = true) {
    AuthenticationUI()
  }
}