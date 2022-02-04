package dev.baseio.slackclone.uichat.chatscreen

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import com.google.accompanist.insets.navigationBarsWithImePadding
import dev.baseio.slackclone.commonui.keyboard.keyboardAsState
import dev.baseio.slackclone.uichat.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ChatScreenContent(viewModel: ChatThreadVM) {
  val checkBoxState by viewModel.chatBoxState.collectAsState()
  val coroutineScope = rememberCoroutineScope()
  val manualExpandValue = if (checkBoxState == BoxState.Expanded) {
    1f
  } else {
    0f
  }
  val dragAnimate = remember {
    Animatable(manualExpandValue)
  }

  LaunchedEffect(key1 = checkBoxState) {
    coroutineScope.launch {
      dragAnimate.animateTo(manualExpandValue)
    }
  }

  val change by animateFloatAsState(
    dragAnimate.targetValue,
    animationSpec = spring(
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
      Modifier
        .layoutId("chatView")
    )
    ChatMessageBox(
      viewModel,
      Modifier
        .animateDrag({
          viewModel.chatBoxState.value = BoxState.Expanded
        }) {
          viewModel.chatBoxState.value = BoxState.Collapsed
        }
        .layoutId("chatBox")
    )
  }
}

@Composable
private fun Modifier.animateDrag(
  onExpand: () -> Unit,
  onCollapse: () -> Unit
): Modifier =
  composed {
    val sensitivity = 200
    var swipeOffset by remember {
      mutableStateOf(0f)
    }
    var gestureConsumed by remember {
      mutableStateOf(false)
    }
    this.pointerInput(Unit) {
      detectVerticalDragGestures(
        onVerticalDrag = { _, dragAmount ->
          //dragAmount: positive when scrolling down; negative when scrolling up
          swipeOffset += dragAmount
          when {
            swipeOffset > sensitivity -> {
              //offset > 0 when swipe down
              if (!gestureConsumed) {
                onCollapse()
                gestureConsumed = true
              }
            }

            swipeOffset < -sensitivity -> {
              //offset < 0 when swipe up
              if (!gestureConsumed) {
                onExpand()
                gestureConsumed = true
              }
            }
          }
        }, onDragEnd = {
          swipeOffset = 0f
          gestureConsumed = false
        })
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
      height = Dimension.fillToConstraints
      width = Dimension.fillToConstraints
      top.linkTo(parent.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
      bottom.linkTo(chatBox.top)
    }
    constrain(chatBox) {
      start.linkTo(parent.start)
      end.linkTo(parent.end)
      bottom.linkTo(parent.bottom)
    }

  }
}
