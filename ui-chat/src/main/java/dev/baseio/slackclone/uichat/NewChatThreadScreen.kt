package dev.baseio.slackclone.uichat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.chatcore.data.UiLayer
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.reusable.SlackListItem
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.SlackScreen

@Composable
fun NewChatThreadScreen(
  newChatThread: NewChatThreadVM = hiltViewModel(),
  composeNavigator: ComposeNavigator
) {
  SlackCloneTheme {
    val scaffoldState = rememberScaffoldState()

    SlackCloneTheme {
      ListRandomUsers(scaffoldState, composeNavigator, newChatThread = newChatThread) {
        composeNavigator.navigate(SlackScreen.CreateNewChannel.name)
      }
    }
  }
}

@Composable
private fun ListRandomUsers(
  scaffoldState: ScaffoldState,
  composeNavigator: ComposeNavigator,
  newChatThread: NewChatThreadVM,
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
        SearchAppBar(composeNavigator)
      },
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
      floatingActionButton = {
        NewChannelFAB(newChannel)
      }
    ) { innerPadding ->
      SearchContent(innerPadding, newChatThread)
    }
  }
}

@Composable
private fun SearchContent(innerPadding: PaddingValues, newChatThread: NewChatThreadVM,) {
  Box(modifier = Modifier.padding(innerPadding)) {
    SlackCloneSurface(
      modifier = Modifier.fillMaxSize()
    ) {
      Column() {
        SearchUsersTF(newChatThread)
        ListAllUsers(newChatThread)
      }
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListAllUsers(newChatThread: NewChatThreadVM,) {
  val userFlows by newChatThread.users.collectAsState()
  val listState = rememberLazyListState()
  LazyColumn(state = listState, reverseLayout = false) {
    var lastDrawnChannel: String? = null
    for (channelIndex in userFlows.indices) {
      val channel = userFlows[channelIndex]
      val newDrawn = channel.name?.first().toString()
      if (canDrawHeader(lastDrawnChannel, newDrawn)) {
        stickyHeader {
          SlackChannelHeader(newDrawn)
        }
      }
      item {
        SlackChannelListItem(channel)
      }
      lastDrawnChannel = newDrawn
    }
  }
}

fun canDrawHeader(lastDrawnChannel: String?, name: String?): Boolean {
  return lastDrawnChannel != name
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SlackChannelListItem(slackChannel: UiLayer.Channels.SlackChannel) {
  Column {
    SlackListItem(
      icon = if (slackChannel.isPrivate == true) Icons.Default.Lock else Icons.Default.MailOutline,
      title = "${slackChannel.name}",
      onItemClick = {
      }
    )
    Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
  }
}

@Composable
fun SlackChannelHeader(title: String) {
  Box(
    Modifier
      .fillMaxWidth()
      .background(SlackCloneColorProvider.colors.lineColor)
  ) {
    Text(
      text = title.toUpperCase(Locale.current),
      modifier = Modifier.padding(12.dp),
      style = SlackCloneTypography.subtitle1.copy(color = SlackCloneColorProvider.colors.textSecondary)
    )
  }
}

@Composable
private fun SearchUsersTF(newChatThread: NewChatThreadVM,) {
  val searchChannel by newChatThread.search.collectAsState()

  TextField(
    value = searchChannel,
    onValueChange = { newValue ->
      newChatThread.search(newValue)
    },
    textStyle = textStyleFieldPrimary(),
    placeholder = {
      Text(
        text = "Search for a channel or conversation",
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
private fun SearchAppBar(composeNavigator: ComposeNavigator) {
  SlackSurfaceAppBar(
    title = {
      SearchNavTitle()
    },
    navigationIcon = {
      NavBackIcon(composeNavigator)
    },
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
  )
}

@Composable
private fun SearchNavTitle() {
  Text(
    text = "New Message",
    style = SlackCloneTypography.subtitle1.copy(color = SlackCloneColorProvider.colors.appBarTextTitleColor)
  )
}

@Composable
private fun NavBackIcon(composeNavigator: ComposeNavigator) {
  IconButton(onClick = {
    composeNavigator.navigateUp()
  }) {
    Icon(
      imageVector = Icons.Filled.Clear,
      contentDescription = "Clear",
      modifier = Modifier.padding(start = 8.dp),
      tint = SlackCloneColorProvider.colors.appBarIconColor
    )
  }
}
