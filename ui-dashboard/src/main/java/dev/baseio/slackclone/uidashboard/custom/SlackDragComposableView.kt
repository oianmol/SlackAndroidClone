package dev.baseio.slackclone.uidashboard.custom

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SlackDragComposableView(
  isLeftNavOpen: Boolean,
  isChatViewClosed: Boolean,
  mainScreenOffset: Float,
  onOpenCloseLeftView: (Boolean) -> Unit,
  onOpenCloseRightView: (Boolean) -> Unit,
  mainScreenComposable: @Composable (modifier: Modifier) -> Unit,
  leftViewComposable: @Composable (modifier: Modifier) -> Unit,
  rightViewComposable: @Composable (modifier: Modifier) -> Unit,
) {
  val chatScreenOffset =
    with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }

  val sideNavOffX = remember {
    Animatable(viewOffset(isLeftNavOpen, mainScreenOffset))
  }
  val chatViewOffX = remember {
    Animatable(viewOffset(isChatViewClosed, chatScreenOffset))
  }

  val coroutineScope = rememberCoroutineScope()

  SideEffect {
    coroutineScope.launch {
      sideNavOffX.animateTo(viewOffset(isLeftNavOpen, mainScreenOffset), animationSpec = tween(250))
    }
    coroutineScope.launch {
      chatViewOffX.animateTo(
        viewOffset(isChatViewClosed, chatScreenOffset),
      )
    }
  }
  Box(Modifier.fillMaxWidth()) {
    leftViewComposable(Modifier)
    mainScreenComposable(
      mainScreenComposable(sideNavOffX, mainScreenOffset, coroutineScope, onOpenCloseLeftView)
    )
    rightViewComposable(
      chatScreenComposable(
        chatViewOffX,
        chatScreenOffset,
        coroutineScope,
        onOpenCloseRightView
      )
    )
  }


}

private fun viewOffset(needsOpen: Boolean, offset: Float) =
  if (needsOpen) offset else 0f


@Composable
private fun chatScreenComposable(
  offsetX: Animatable<Float, AnimationVector1D>,
  requiredOffset: Float,
  coroutineScope: CoroutineScope,
  onOpen: (Boolean) -> Unit,
) = Modifier
  .offset {
    IntOffset(
      (offsetX.value).roundToInt(),
      0
    )
  }
  .pointerInput(Unit) {
    detectHorizontalDragGestures({
      //start
    }, {
      //end
      if (offsetX.targetValue > requiredOffset / 2) {
        coroutineScope.launch {
          offsetX.animateTo(requiredOffset, animationSpec = tween(150))
          onOpen(true)
        }
      } else {
        coroutineScope.launch {
          offsetX.animateTo(0F, animationSpec = tween(150))
          onOpen(false)
        }
      }
    }, {
      //cancel

    }, { change, dragAmount ->
      val summed = Offset(x = offsetX.targetValue + dragAmount, y = 0f)
      val newDragValue = Offset(x = summed.x.coerceIn(0f, requiredOffset), y = 0f)
      change.consumePositionChange()
      coroutineScope.launch {
        offsetX.animateTo(newDragValue.x, animationSpec = tween(50))
      }

    })
  }

@Composable
private fun mainScreenComposable(
  offsetX: Animatable<Float, AnimationVector1D>,
  dragOffset: Float,
  coroutineScope: CoroutineScope,
  onOpen: (Boolean) -> Unit,
) = Modifier
  .offset {
    IntOffset(
      (offsetX.value).roundToInt(),
      0
    )
  }
  .pointerInput(Unit) {
    detectHorizontalDragGestures({
      //start
    }, {
      //end
      if (offsetX.targetValue > dragOffset / 2) {
        coroutineScope.launch {
          offsetX.animateTo(dragOffset, animationSpec = tween(150))
          onOpen(true)
        }
      } else {
        coroutineScope.launch {
          offsetX.animateTo(0F, animationSpec = tween(150))
          onOpen(false)
        }
      }
    }, {
      //cancel

    }, { change, dragAmount ->
      val summed = Offset(x = offsetX.targetValue + dragAmount, y = 0f)
      val newDragValue = Offset(x = summed.x.coerceIn(0f, dragOffset), y = 0f)
      change.consumePositionChange()
      coroutineScope.launch {
        offsetX.animateTo(newDragValue.x, animationSpec = tween(50))
      }

    })
  }