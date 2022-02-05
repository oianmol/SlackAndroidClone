package dev.baseio.slackclone.uichannels.createsearch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.uichannels.R

@Composable
fun CreateNewChannelUI(
  composeNavigator: ComposeNavigator,
  createChannelVM: CreateChannelVM = hiltViewModel()
) {
  SlackCloneTheme {
    val scaffoldState = rememberScaffoldState()

    SlackCloneTheme {
      CreateChannel(scaffoldState, composeNavigator, createChannelVM = createChannelVM)
    }
  }
}

@Composable
private fun CreateChannel(
  scaffoldState: ScaffoldState,
  composeNavigator: ComposeNavigator,
  createChannelVM: CreateChannelVM,
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
        NewChannelAppBar(composeNavigator, createChannelVM)
      },
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
    ) { innerPadding ->
      NewChannelContent(innerPadding, createChannelVM)
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NewChannelContent(innerPadding: PaddingValues, createChannelVM: CreateChannelVM) {
  val searchChannel by createChannelVM.channel.collectAsState()

  Box(modifier = Modifier.padding(innerPadding)) {
    SlackCloneSurface(
      modifier = Modifier.fillMaxSize()
    ) {
      val scroll = rememberScrollState()

      Column(Modifier.verticalScroll(scroll)) {
        Name()
        NameField(createChannelVM)
        Divider(color = SlackCloneColorProvider.colors.lineColor)
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        PrivateChannel(searchChannel.isPrivate ?: false, createChannelVM)
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        Divider(color = SlackCloneColorProvider.colors.lineColor)
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        ShareOutSideOrg(searchChannel.isShareOutSide ?: false, createChannelVM)
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        Divider(color = SlackCloneColorProvider.colors.lineColor)
      }
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ShareOutSideOrg(
  isChecked: Boolean,
  createChannelVM: CreateChannelVM
) {
  ListItem(text = {
    Text(text = stringResource(R.string.share_outside), style = textStyleFieldPrimary())
  }, secondaryText = {
    Text(
      text = stringResource(R.string.share_outside_subtitle),
      style = textStyleFieldSecondary()
    )
  }, trailing = {
    Checkbox(
      checked = isChecked, onCheckedChange = {
        createChannelVM.channel.value = createChannelVM.channel.value.copy(isShareOutSide = it)
      }, colors = CheckboxDefaults.colors(
        checkedColor = Color.LightGray,
        uncheckedColor = Color.LightGray,
        checkmarkColor = SlackCloneColorProvider.colors.brand,
      )
    )
  })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PrivateChannel(
  isChecked: Boolean,
  createChannelVM: CreateChannelVM
) {
  ListItem(text = {
    Text(text = stringResource(R.string.make_private), style = textStyleFieldPrimary())
  }, secondaryText = {
    Text(
      text = if (isChecked) stringResource(R.string.make_private_subtitle_checked) else stringResource(
        R.string.make_private_subtitle
      ),
      style = textStyleFieldSecondary()
    )
  }, trailing = {
    Switch(
      checked = isChecked, onCheckedChange = {
        createChannelVM.channel.value = createChannelVM.channel.value.copy(isPrivate = it)
      }, colors = SwitchDefaults.colors(
        checkedThumbColor = SlackCloneColorProvider.colors.accent,
        uncheckedThumbColor = Color.LightGray,
        checkedTrackColor = SlackCloneColorProvider.colors.accent,
        uncheckedTrackColor = Color.LightGray,
        checkedTrackAlpha = 0.2f
      )
    )
  }, modifier = Modifier.padding(8.dp))
}


@Composable
private fun Name() {
  Text(
    text = stringResource(R.string.Name),
    style = textStyleFieldPrimary(),
    modifier = Modifier.padding(8.dp)
  )
}

@Composable
private fun NameField(createChannelVM: CreateChannelVM) {
  val searchChannel by createChannelVM.channel.collectAsState()

  TextField(
    value = searchChannel.name ?: "",
    onValueChange = { newValue ->
      val newId = newValue.replace(" ", "-")
      createChannelVM.channel.value =
        createChannelVM.channel.value.copy(name = newId, uuid = newId)
    },
    textStyle = textStyleFieldPrimary(),
    leadingIcon = {
      Text(text = "#", style = textStyleFieldSecondary())
    },
    trailingIcon = {
      Text(text = "${80 - (searchChannel.name?.length ?: 0)}", style = textStyleFieldSecondary())
    },
    placeholder = {
      Text(
        text = "e.g. plan-budget",
        style = textStyleFieldSecondary(),
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
      )
    },
    colors = textFieldColors(),
    singleLine = true,
    maxLines = 1,
    modifier = Modifier.fillMaxWidth()
  )
}

@Composable
private fun textStyleFieldPrimary() = SlackCloneTypography.subtitle1.copy(
  color = SlackCloneColorProvider.colors.textPrimary,
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start
)


@Composable
private fun textStyleFieldSecondary() = SlackCloneTypography.subtitle2.copy(
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
private fun NewChannelAppBar(composeNavigator: ComposeNavigator, createChannelVM: CreateChannelVM) {
  val haptic = LocalHapticFeedback.current
  SlackSurfaceAppBar(
    title = {
      NavTitle()
    },
    navigationIcon = {
      NavBackIcon(composeNavigator)
    },
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
    actions = {
      TextButton(onClick = {
        createChannelVM.channel.value.name?.takeIf { it.isNotEmpty() }?.let {
          createChannelVM.createChannel()
        } ?: run {
          haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
      }) {
        Text(
          stringResource(R.string.create),
          style = textStyleFieldSecondary().copy(color = SlackCloneColorProvider.colors.appBarTextSubTitleColor)
        )
      }
    }
  )
}

@Composable
private fun NavTitle() {
  Text(
    text = "New Channel",
    style = SlackCloneTypography.subtitle1.copy(color = SlackCloneColorProvider.colors.appBarTextTitleColor)
  )
}

@Composable
private fun NavBackIcon(composeNavigator: ComposeNavigator) {
  IconButton(onClick = {
    composeNavigator.navigateUp()
  }) {
    Icon(
      imageVector = Icons.Filled.ArrowBack,
      contentDescription = "Clear",
      modifier = Modifier.padding(start = 8.dp),
      tint = SlackCloneColorProvider.colors.appBarIconColor
    )
  }
}
