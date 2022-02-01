package dev.baseio.slackclone.uichat

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.keyboard.Keyboard
import dev.baseio.slackclone.commonui.keyboard.keyboardAsState
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.reusable.SlackImageBox
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.domain.model.message.SlackMessage
import dev.baseio.slackclone.uichat.models.ChatPresentation
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ChatScreenUI(
  modifier: Modifier,
  slackChannel: ChatPresentation.SlackChannel,
  onBackClick: () -> Unit,
  viewModel: ChatThreadVM = hiltViewModel()
) {
  val scaffoldState = rememberScaffoldState()
  SlackCloneTheme {
    Scaffold(
      backgroundColor = SlackCloneColorProvider.colors.uiBackground,
      contentColor = SlackCloneColorProvider.colors.textSecondary,
      modifier = modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
      topBar = {
        ChatAppBar(onBackClick, slackChannel)
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .padding(innerPadding)
      ) {

        ConstraintLayout(
          modifier = Modifier
            .navigationBarsWithImePadding()
            .fillMaxHeight()
            .fillMaxWidth()
        ) {
          // Create references for the composables to constrain
          val isExpanded by viewModel.isExpanded.collectAsState()
          val isKeyboardOpen by keyboardAsState()

          Column() {
            if(!isExpanded){
              ChatMessagesUI(
                viewModel,
                modifier = Modifier.weight(1f)
              )
            }
            ChatMessageBox(
              viewModel, if (isExpanded) Modifier.weight(1f) else Modifier
            )
            if (isKeyboardOpen == Keyboard.Opened) {
              ChatOptions(viewModel, Modifier)
            }
          }
        }

      }
    }
  }
}

fun expandedBottomViewConstrains(
  constraintLayoutScope: ConstraintLayoutScope,
  bottomView: ConstrainedLayoutReference,
  chatOptions: ConstrainedLayoutReference
): Modifier {
  constraintLayoutScope.apply {
    return Modifier.constrainAs(bottomView) {
      top.linkTo(parent.top)
      bottom.linkTo(chatOptions.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
    }
  }
}

fun normalBottomViewConstraints(
  constraintLayoutScope: ConstraintLayoutScope,
  bottomView: ConstrainedLayoutReference,
  chatView: ConstrainedLayoutReference,
  chatOptions: ConstrainedLayoutReference
): Modifier {
  constraintLayoutScope.apply {
    return Modifier.constrainAs(bottomView) {
      top.linkTo(chatView.bottom)
      bottom.linkTo(chatOptions.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
    }
  }
}

private fun normalChatConstraints(
  chatView: ConstrainedLayoutReference,
  bottomView: ConstrainedLayoutReference,
  constraintLayoutScope: ConstraintLayoutScope
): Modifier {
  constraintLayoutScope.apply {
    return Modifier.Companion.constrainAs(chatView) {
      top.linkTo(parent.top)
      bottom.linkTo(bottomView.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
    }
  }
}

private fun expandedChatConstraints(
  chatView: ConstrainedLayoutReference,
  constraintLayoutScope: ConstraintLayoutScope
): Modifier {
  constraintLayoutScope.apply {
    return Modifier.Companion.constrainAs(chatView) {
      top.linkTo(parent.top)
      bottom.linkTo(parent.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
    }
  }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatMessageBox(viewModel: ChatThreadVM, modifier: Modifier) {
  val isKeyboardOpen by keyboardAsState()

  Column(modifier.background(SlackCloneColorProvider.colors.uiBackground)) {
    Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
    MessageTFRow(viewModel, isKeyboardOpen)
  }

}

@Composable
fun ChatOptions(viewModel: ChatThreadVM, modifier: Modifier = Modifier) {
  val search by viewModel.message.collectAsState()

  Row(modifier) {
    IconButton(onClick = { /*TODO*/ }) {
      Icon(Icons.Outlined.Add, contentDescription = null, chatOptionIconSize())
    }
    IconButton(onClick = { /*TODO*/ }) {
      Icon(Icons.Outlined.AccountCircle, contentDescription = null, chatOptionIconSize())
    }
    IconButton(onClick = { /*TODO*/ }) {
      Icon(Icons.Outlined.Email, contentDescription = null, chatOptionIconSize())
    }
    IconButton(onClick = { /*TODO*/ }) {
      Icon(Icons.Outlined.ShoppingCart, contentDescription = null, chatOptionIconSize())
    }
    IconButton(onClick = { /*TODO*/ }) {
      Icon(Icons.Outlined.Phone, contentDescription = null, chatOptionIconSize())
    }
    IconButton(onClick = { /*TODO*/ }) {
      Icon(Icons.Outlined.MailOutline, contentDescription = null, chatOptionIconSize())
    }
    SendMessageButton(viewModel = viewModel, search = search, modifier = Modifier.weight(1f))

  }
}

private fun chatOptionIconSize() = Modifier.size(20.dp)

@Composable
private fun MessageTFRow(
  viewModel: ChatThreadVM,
  isKeyboardOpen: Keyboard
) {
  val search by viewModel.message.collectAsState()

  Row(Modifier.padding(start = 4.dp)) {
    BasicTextField(
      value = search,
      cursorBrush = SolidColor(SlackCloneColorProvider.colors.textPrimary),
      onValueChange = {
        viewModel.message.value = it
      },
      textStyle = SlackCloneTypography.subtitle1.copy(
        color = Color.White,
      ),
      decorationBox = { innerTextField ->
        ChatTFPlusPlaceHolder(search, innerTextField)
      },
      modifier = Modifier.weight(1f)
    )
    if (isKeyboardOpen == Keyboard.Closed) {
      SendMessageButton(viewModel, search)
    } else {
      CollapseExpandButton(viewModel)
    }
  }
}

@Composable
fun CollapseExpandButton(viewModel: ChatThreadVM) {
  val isExpanded by viewModel.isExpanded.collectAsState()
  IconButton(
    onClick = {
      viewModel.isExpanded.value = !viewModel.isExpanded.value
    },
  ) {
    Icon(
      if (!isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
      contentDescription = null,
    )
  }
}

@Composable
private fun SendMessageButton(
  viewModel: ChatThreadVM,
  search: String,
  modifier: Modifier = Modifier
) {
  IconButton(
    onClick = {
      viewModel.sendMessage(search)
      viewModel.message.value = ""
    }, enabled = search.isNotEmpty(), modifier = modifier
  ) {
    Icon(
      Icons.Default.Send,
      contentDescription = null,
      tint = if (search.isEmpty()) SlackCloneColorProvider.colors.sendButtonDisabled else SlackCloneColorProvider.colors.sendButtonEnabled
    )
  }
}

@Composable
private fun ChatTFPlusPlaceHolder(
  search: String,
  innerTextField: @Composable () -> Unit
) {
  Row(
    Modifier
      .padding(16.dp), verticalAlignment = Alignment.CenterVertically
  ) {
    if (search.isEmpty()) {
      Text(
        text = "Message #jetpack_compose",
        style = SlackCloneTypography.subtitle1.copy(
          color = SlackCloneColorProvider.colors.textSecondary,
        ),
        modifier = Modifier.weight(1f)
      )
    } else {
      innerTextField()
    }
  }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ChatMessagesUI(viewModel: ChatThreadVM, modifier: Modifier) {
  val messages = viewModel.chatMessagesFlow.collectAsLazyPagingItems()
  val listState = rememberLazyListState()

  LazyColumn(state = listState, reverseLayout = true, modifier = modifier) {
    items(messages) { message ->
      message?.let {
        Column {
          ChatHeader(message.createdDate)
          ChatMessage(message)
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChatMessage(message: SlackMessage) {
  ListItem(icon = {
    SlackImageBox(Modifier.size(48.dp), imageUrl = "http://placekitten.com/200/300")
  }, modifier = Modifier.padding(2.dp), secondaryText = {
    ChatMedia(message)
  }, text = {
    ChatUserDateTime(message)
  })
}

@Composable
private fun ChatMedia(message: SlackMessage) {
  Column {
    Text(
      message.message,
      style = SlackCloneTypography.subtitle2.copy(
        color = SlackCloneColorProvider.colors.textSecondary
      ), modifier = Modifier.padding(4.dp)
    )
    SlackImageBox(
      modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(228.dp), imageUrl = "http://placekitten.com/300/300"
    )
  }

}

@Composable
private fun ChatUserDateTime(message: SlackMessage) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Text(
      message.createdBy + " \uD83C\uDF34",
      style = SlackCloneTypography.subtitle1.copy(
        fontWeight = FontWeight.Bold,
        color = SlackCloneColorProvider.colors.textPrimary
      ), modifier = Modifier.padding(4.dp)
    )
    Text(
      message.createdDate.calendar().formattedTime(),
      style = SlackCloneTypography.overline.copy(
        color = SlackCloneColorProvider.colors.textSecondary.copy(alpha = 0.8f)
      ), modifier = Modifier.padding(4.dp)
    )
  }
}

@Composable
fun ChatHeader(createdDate: Long) {
  Column(Modifier.padding(start = 8.dp, end = 8.dp)) {
    Text(
      createdDate.calendar().formattedMonthDate(),
      style = SlackCloneTypography.subtitle2.copy(
        fontWeight = FontWeight.Bold,
        color = SlackCloneColorProvider.colors.textPrimary
      ), modifier = Modifier.padding(4.dp)
    )
    Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
  }
}

@SuppressLint("SimpleDateFormat")
private fun Calendar.formattedMonthDate(): String {
  return SimpleDateFormat("MMM dd").format(this.time)
}

@SuppressLint("SimpleDateFormat")
private fun Calendar.formattedTime(): String {
  return SimpleDateFormat("hh:mm a").format(this.time)
}

private fun Long.calendar(): Calendar {
  return Calendar.getInstance().apply {
    this.timeInMillis = this@calendar
  }
}

private fun Long?.month(): Int? {
  this?.let {
    return it.calendar().get(Calendar.MONTH)
  } ?: kotlin.run {
    return null
  }

}

@Composable
private fun ChatAppBar(onBackClick: () -> Unit, slackChannel: ChatPresentation.SlackChannel) {
  SlackSurfaceAppBar(backgroundColor = SlackCloneColorProvider.colors.appBarColor) {
    IconButton(onClick = { onBackClick() }) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        tint = SlackCloneColorProvider.colors.appBarIconColor,
        modifier = Modifier.size(24.dp)
      )
    }
    Column(
      Modifier.weight(1f),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = " ${if (slackChannel.isPrivate) lock() else "#"}  ${slackChannel.name}",
        style = SlackCloneTypography.subtitle1.copy(
          fontWeight = FontWeight.Bold,
          color = SlackCloneColorProvider.colors.appBarTextTitleColor
        ), modifier = Modifier.padding(2.dp)
      )
      Text(
        text = "25 members >",
        style = SlackCloneTypography.caption.copy(
          fontWeight = FontWeight.Normal,
          color = SlackCloneColorProvider.colors.appBarTextSubTitleColor
        ),
        modifier = Modifier.padding(2.dp)
      )
    }
    IconButton(onClick = { }) {
      Icon(
        imageVector = Icons.Default.Call,
        contentDescription = null,
        tint = SlackCloneColorProvider.colors.appBarIconColor,
        modifier = Modifier
          .size(24.dp)
      )
    }
  }
}

fun lock() = "\uD83D\uDD12"