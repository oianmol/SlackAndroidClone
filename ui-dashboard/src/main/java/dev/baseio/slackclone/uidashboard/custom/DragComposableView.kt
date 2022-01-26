package dev.baseio.slackclone.uidashboard.custom

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun DragComposableView(
  isOpen: Boolean,
  dragOffset: Float,
  onOpen: () -> Unit,
  onClose: () -> Unit,
  dragComposable: @Composable (modifier: Modifier) -> Unit,
) {
  var offsetX by remember { mutableStateOf(if (isOpen) dragOffset else 0f) }

  SideEffect{
    offsetX = if (isOpen) dragOffset else 0f
  }

  dragComposable(
    Modifier
      .offset { IntOffset((offsetX).roundToInt(), 0) }
      .pointerInput(Unit) {
        detectHorizontalDragGestures({
          //start
        }, {
          //end
          if (offsetX > dragOffset / 2) {
            offsetX = dragOffset
            onOpen()
          } else {
            offsetX = 0f
            onClose()
          }
        }, {
          //cancel

        }, { change, dragAmount ->
          val original = Offset(offsetX, 0f)
          val summed = original + Offset(x = dragAmount, y = 0f)
          val newValue = Offset(x = summed.x.coerceIn(0f, dragOffset), y = 0f)
          change.consumePositionChange()
          offsetX = newValue.x
        })
      })

}