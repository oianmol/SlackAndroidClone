package dev.baseio.slackclone.uichat.chatscreen

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumePositionChange
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.abs

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ChatScreenContent(viewModel: ChatThreadVM) {
  val checkBoxState by viewModel.chatBoxState.collectAsState()
  val coroutineScope = rememberCoroutineScope()
  val keyboardState by keyboardAsState()
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

  val height = with(LocalDensity.current) {
    LocalConfiguration.current.screenHeightDp.dp.toPx()
  }

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
        .animateDrag(height, coroutineScope, dragAnimate,{
          viewModel.chatBoxState.value = BoxState.Expanded
        }) {
          viewModel.chatBoxState.value = BoxState.Collapsed
        }
        .layoutId("chatBox")
    )
  }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun Modifier.animateDrag(
  height: Float,
  coroutineScope: CoroutineScope,
  dragAnimate: Animatable<Float, AnimationVector1D>,
  onExpand: () -> Unit,
  onCollapse: () -> Unit
): Modifier = pointerInput(Unit) {
  detectVerticalDragGestures(onVerticalDrag = { change, dragAmount ->
    val percentageDrag = (abs(change.position.y).div(height))
    change.consumePositionChange()
    coroutineScope.launch {
      dragAnimate.snapTo(percentageDrag)
    }
  }, onDragEnd = {
    if (dragAnimate.value > 0.1f) {
      coroutineScope.launch {
        dragAnimate.animateTo(1f, animationSpec = tween(250))
        onExpand()
      }
    } else {
      coroutineScope.launch {
        dragAnimate.animateTo(0f, animationSpec = tween(250))
        onCollapse()
      }
    }

  })
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
