package dev.baseio.slackclone.uidashboard.custom

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DragComposableView(
  isOpen: Boolean,
  dragOffset: Float,
  onOpen: () -> Unit,
  onClose: () -> Unit,
  dragComposable: @Composable (modifier: Modifier) -> Unit,
) {

  val offsetX = remember {
    Animatable(if (isOpen) dragOffset else 0f)
  }
  val coroutineScope = rememberCoroutineScope()

  SideEffect {
    coroutineScope.launch {
      offsetX.animateTo(if (isOpen) dragOffset else 0f, animationSpec = tween(250))
    }
  }
  dragComposable(
    Modifier
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
              onOpen()
            }
          } else {
            coroutineScope.launch {
              offsetX.animateTo(0F, animationSpec = tween(150))
              onClose()
            }
          }
        }, {
          //cancel

        }, { change, dragAmount ->
          val original = Offset(offsetX.targetValue, 0f)
          val summed = original + Offset(x = dragAmount, y = 0f)
          val newValue = Offset(x = summed.x.coerceIn(0f, dragOffset), y = 0f)
          change.consumePositionChange()
          coroutineScope.launch {
            offsetX.animateTo(newValue.x, animationSpec = tween(50))
          }

        })
      })

}