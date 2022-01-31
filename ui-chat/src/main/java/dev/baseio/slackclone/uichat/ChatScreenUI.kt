package dev.baseio.slackclone.uichat

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.reusable.SlackImageBox
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.data.local.model.DBSlackMessage
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
          .navigationBarsWithImePadding()
      ) {
        Column {
          Box(Modifier.weight(1f)) {
            ChatMessagesUI(viewModel)
          }
          Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
          ChatMessageBox()
        }
      }
    }
  }
}

@Composable
private fun textStyleField() = SlackCloneTypography.h6.copy(
  color = SlackCloneColorProvider.colors.textPrimary,
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start
)

@Composable
fun ChatMessageBox() {
  var search by remember { mutableStateOf("") }

  Row(Modifier.padding(start = 4.dp)) {
    BasicTextField(
      value = search,
      maxLines = 5,
      onValueChange = {
        search = it
      },
      textStyle = SlackCloneTypography.subtitle1.copy(
        color = Color.White,
      ),
      decorationBox = { innerTextField ->
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
      },
      modifier = Modifier.weight(1f)
    )
    IconButton(onClick = { }) {
      Icon(Icons.Default.Send, contentDescription = null)
    }
  }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ChatMessagesUI(viewModel: ChatThreadVM) {
  val messages = viewModel.chatMessagesFlow.collectAsLazyPagingItems()
  val listState = rememberLazyListState()

  LazyColumn(state = listState, reverseLayout = true) {
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