package dev.baseio.slackclone.auth.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.slackclone.commonui.material.DefaultSnackbar
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.auth.R
import dev.baseio.slackclone.auth.vm.AuthVM
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator

@Composable
fun AuthenticationUI(
  authVM: AuthVM = hiltViewModel(),
  composeNavigator: ComposeNavigator,
  navigatorFragment: FragmentNavGraphNavigator
) {
  val scaffoldState = rememberScaffoldState()

  val sysUiController = rememberSystemUiController()

  LaunchedEffect(Unit) {
    sysUiController.setNavigationBarColor(color = SlackCloneColor)
    sysUiController.setSystemBarsColor(color = SlackCloneColor)
  }
  Scaffold(
    backgroundColor = SlackCloneTheme.colors.uiBackground,
    contentColor = SlackCloneTheme.colors.textSecondary,
    modifier = Modifier.statusBarsPadding(), scaffoldState = scaffoldState, snackbarHost = {
      scaffoldState.snackbarHostState
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      AuthSurface(
        authVM = authVM, scaffoldState = scaffoldState, navigatorFragment
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
  scaffoldState: ScaffoldState,
  navigatorFragment: FragmentNavGraphNavigator
) {
  SlackCloneSurface(
    color = SlackCloneColor,
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

      AnimatedVisibility(visible = canShowForm(formVisible)) {
        EmailTF(authVM, focusRequester)
      }

      AnimatedVisibility(visible = canShowForm(formVisible)) {
        PasswordTF(authVM, focusRequester)
      }

      AnimatedVisibility(visible = (formVisible is AuthVM.UiState.LoadingState)) {
        CircularProgressIndicator(modifier = Modifier.padding(8.dp), color = Color.White)
      }

      AnimatedVisibility(visible = canShowForm(formVisible)) {
        LoginButton(authVM = authVM)
      }

      AnimatedVisibility(visible = canShowForm(formVisible)) {
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

private fun canShowForm(formVisible: AuthVM.UiState) =
  formVisible is AuthVM.UiState.Empty || formVisible is AuthVM.UiState.ErrorState

@Composable
fun ForgotPasswordText(authVM: AuthVM) {
  ClickableText(text = buildAnnotatedString {

    withStyle(
      style = SpanStyle(
        color = Color.White,
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
    },
    Modifier
      .fillMaxWidth()
      .height(50.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = SlackCloneTheme.colors.buttonColor)
  ) {
    Text(
      text = "Login",
      style = SlackCloneTypography.body1.copy(color = SlackCloneTheme.colors.buttonTextColor)
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
        style = SlackCloneTypography.body2.copy(color = SlackCloneTheme.colors.textPrimary)
      )
    },
    shape = SlackCloneShapes.large,
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
private fun EmailTF(authVM: AuthVM, focusRequester: FocusRequester) {
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
        style = SlackCloneTypography.body2.copy(color = SlackCloneTheme.colors.textPrimary)
      )
    },
    shape = SlackCloneShapes.large,
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
  backgroundColor = Color.White,
)