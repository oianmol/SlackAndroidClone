package dev.baseio.slackclone.uichannels.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.SlackScreen

@Composable
fun SearchCreateChannelUI(
  composeNavigator: ComposeNavigator
) {
  SlackCloneTheme {
    val scaffoldState = rememberScaffoldState()

    SlackCloneTheme {
      ListChannels(scaffoldState, composeNavigator) {
        composeNavigator.navigate(SlackScreen.CreateNewChannel.name)
      }
    }
  }
}

@Composable
fun ListChannels(
  scaffoldState: ScaffoldState,
  composeNavigator: ComposeNavigator,
  newChannel: () -> Unit
) {
  Box {
    Scaffold(
      backgroundColor = SlackCloneColorProvider.colors.uiBackground,
      contentColor = SlackCloneColorProvider.colors.textSecondary,
      modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
      scaffoldState = scaffoldState,
      topBar = {
        AppBar(composeNavigator)
      },
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
      floatingActionButton = {
        NewChannelFAB(newChannel)
      }
    ) { innerPadding ->
      Content(innerPadding)
    }
  }
}

@Composable
private fun Content(innerPadding: PaddingValues) {
  Box(modifier = Modifier.padding(innerPadding)) {
    SlackCloneSurface(
      modifier = Modifier.fillMaxSize()
    ) {
      val scroll = rememberScrollState()
      Column(Modifier.verticalScroll(scroll)) {
        SearchChannelsTF()
      }
    }
  }
}

@Composable
private fun SearchChannelsTF() {
  var searchChannel by remember { mutableStateOf("") }

  TextField(
    value = searchChannel,
    onValueChange = { newValue ->
      searchChannel = newValue
    },
    textStyle = textStyleFieldPrimary(),
    placeholder = {
      Text(
        text = "Search for channels",
        style = textStyleFieldSecondary(),
        textAlign = TextAlign.Start
      )
    },
    colors = textFieldColors(),
    singleLine = true,
    maxLines = 1,
  )
}

@Composable
private fun textStyleFieldPrimary() = SlackCloneTypography.subtitle1.copy(
  color = SlackCloneColorProvider.colors.textPrimary,
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start
)


@Composable
private fun textStyleFieldSecondary() = SlackCloneTypography.subtitle1.copy(
  color = SlackCloneColorProvider.colors.textSecondary,
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start
)

@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
  backgroundColor = Color.Transparent,
  cursorColor = SlackCloneColorProvider.colors.textPrimary,
  unfocusedIndicatorColor = Color.Transparent,
  focusedIndicatorColor = Color.Transparent
)

@Composable
private fun NewChannelFAB(newChannel: () -> Unit) {
  FloatingActionButton(onClick = {
    newChannel()
  }, backgroundColor = Color.White) {
    Icon(
      imageVector = Icons.Default.Add,
      contentDescription = null,
      tint = SlackCloneColor
    )
  }
}

@Composable
private fun AppBar(composeNavigator: ComposeNavigator) {
  SlackSurfaceAppBar(
    title = {
      NavTitle()
    },
    navigationIcon = {
      NavBackIcon(composeNavigator)
    },
    backgroundColor = SlackCloneColorProvider.colors.uiBackground,
  )
}

@Composable
private fun NavTitle() {
  Column {
    Text(
      text = "Channel Browser",
      style = SlackCloneTypography.subtitle1.copy(color = SlackCloneColorProvider.colors.appBarTextTitleColor)
    )
    Text(
      text = "400 channels",
      style = SlackCloneTypography.subtitle2.copy(color = SlackCloneColorProvider.colors.appBarTextSubTitleColor)
    )
  }
}

@Composable
private fun NavBackIcon(composeNavigator: ComposeNavigator) {
  IconButton(onClick = {
    composeNavigator.navigateUp()
  }) {
    Icon(
      imageVector = Icons.Filled.Clear,
      contentDescription = "Clear",
      modifier = Modifier.padding(start = 8.dp), tint = Color.White
    )
  }
}
