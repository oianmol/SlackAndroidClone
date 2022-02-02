package dev.baseio.slackclone.uichat.chatscreen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import com.google.accompanist.insets.navigationBarsWithImePadding
import dev.baseio.slackclone.uichat.*

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ChatScreenContent(viewModel: ChatThreadVM) {
  val checkBoxState by viewModel.chatBoxState.collectAsState()

  val change by animateFloatAsState(
    if (checkBoxState == BoxState.Expanded) {
      1f
    } else {
      0f
    }, animationSpec = spring(
      dampingRatio = Spring.DampingRatioLowBouncy,
      stiffness = Spring.StiffnessMediumLow
    )
  )

  MotionLayout(
    start = chatConstrains(),
    end = chatConstrainsExpanded(),
    progress = change,
    modifier = Modifier
      .navigationBarsWithImePadding()
      .fillMaxHeight()
      .fillMaxWidth()
  ) {
    ChatMessagesUI(
      viewModel,
      Modifier.fillMaxHeight().fillMaxWidth()
        .layoutId("chatView")
    )
    ChatMessageBox(
      viewModel,
      Modifier
        .layoutId("chatBox")
    )
  }
}

private fun chatConstrainsExpanded(): ConstraintSet {
  return ConstraintSet {
    val chatBox = createRefFor("chatBox")
    val chatView = createRefFor("chatView")
    constrain(chatView) {
      top.linkTo(parent.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
      bottom.linkTo(parent.bottom)
    }
    constrain(chatBox) {
      height = Dimension.fillToConstraints
      top.linkTo(parent.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
      bottom.linkTo(parent.bottom)
    }

  }
}

private fun chatConstrains(): ConstraintSet {
  return ConstraintSet {
    val chatBox = createRefFor("chatBox")
    val chatView = createRefFor("chatView")
    constrain(chatView) {
      top.linkTo(parent.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
      bottom.linkTo(chatBox.top, margin = 48.dp)
    }
    constrain(chatBox) {
      start.linkTo(parent.start)
      end.linkTo(parent.end)
      bottom.linkTo(parent.bottom)
    }

  }
}
