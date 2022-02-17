package dev.baseio.slackclone.commonui.reusable

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SlackDragComposableView(
    isLeftNavOpen: Boolean,
    isChatViewClosed: Boolean,
    mainScreenOffset: Float,
    chatScreenOffset: Float,
    onOpenCloseLeftView: (Boolean) -> Unit,
    onOpenCloseRightView: (Boolean) -> Unit,
    leftViewComposable: @Composable (modifier: Modifier) -> Unit,
    rightViewComposable: @Composable (modifier: Modifier) -> Unit,
    mainScreenComposable: @Composable (modifier: Modifier) -> Unit
) {
    val sideNavOffX = remember {
        Animatable(viewOffset(isLeftNavOpen, mainScreenOffset))
    }
    val chatViewOffX = remember {
        Animatable(viewOffset(isChatViewClosed, chatScreenOffset))
    }

    val coroutineScope = rememberCoroutineScope()

    InitialOffsetsSideEffect(
        coroutineScope,
        sideNavOffX,
        isLeftNavOpen,
        mainScreenOffset,
        chatViewOffX,
        isChatViewClosed,
        chatScreenOffset
    )

    Box(Modifier.fillMaxWidth()) {
        leftViewComposable(Modifier)
        mainScreenComposable(
            mainScreenModifier(
                sideNavOffX,
                mainScreenOffset,
                coroutineScope,
                onOpenCloseLeftView,
                onOpenCloseRightView,
                chatViewOffX,
                chatScreenOffset
            )
        )
        rightViewComposable(
            chatScreenModifier(
                chatViewOffX,
                chatScreenOffset,
                coroutineScope,
                onOpenCloseRightView
            )
        )
    }
}

@Composable
private fun InitialOffsetsSideEffect(
    coroutineScope: CoroutineScope,
    sideNavOffX: Animatable<Float, AnimationVector1D>,
    isLeftNavOpen: Boolean,
    mainScreenOffset: Float,
    chatViewOffX: Animatable<Float, AnimationVector1D>,
    isChatViewClosed: Boolean,
    chatScreenOffset: Float
) {
    SideEffect {
        coroutineScope.launch {
            sideNavOffX.snapTo(viewOffset(isLeftNavOpen, mainScreenOffset))
        }
        coroutineScope.launch {
            chatViewOffX.snapTo(viewOffset(isChatViewClosed, chatScreenOffset))
        }
    }
}

private fun viewOffset(needsOpen: Boolean, offset: Float) =
    if (needsOpen) offset else 0f


@Composable
private fun chatScreenModifier(
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
        detectHorizontalDragGestures(
          onDragStart = {
            //start
          },
          onDragEnd = {
            rightViewEndTransition(offsetX, requiredOffset, coroutineScope, onOpen)
          },
          onDragCancel = {
            //cancel
          },
          onHorizontalDrag = { change, dragAmount ->
            // this moves the chat view left/right
            val summedMain = Offset(x = offsetX.targetValue + dragAmount, y = 0f)
            val newDragValueMain = Offset(x = summedMain.x.coerceIn(0f, requiredOffset), y = 0f)
            change.consumePositionChange()
            coroutineScope.launch {
                offsetX.animateTo(newDragValueMain.x, animationSpec = tween(50))
            }
        })
    }

private fun mainScreenModifier(
    offsetX: Animatable<Float, AnimationVector1D>,
    dragOffset: Float,
    coroutineScope: CoroutineScope,
    onOpenSideNav: (Boolean) -> Unit,
    onOpenCloseRightView: (Boolean) -> Unit,
    chatViewOffX: Animatable<Float, AnimationVector1D>,
    chatScreenOffset: Float,
) = Modifier
    .offset {
        IntOffset(
            (offsetX.value).roundToInt(),
            0
        )
    }
    .pointerInput(Unit) {
        detectHorizontalDragGestures(
          onDragStart = {
            //start
          },
          onDragEnd = {
            sideNavigationEndTransition(
                offsetX,
                dragOffset,
                coroutineScope,
                onOpenSideNav
            )
            rightViewEndTransition(
                chatViewOffX,
                chatScreenOffset,
                coroutineScope,
                onOpenCloseRightView
            )
          },
          onDragCancel = {
            //cancel
          },
          onHorizontalDrag = { change, dragAmount ->
              mainAnimateOffset(
                  offsetX,
                  dragAmount,
                  dragOffset,
                  change,
                  coroutineScope,
                  chatViewOffX,
                  chatScreenOffset
              )
          }
        )
    }

fun rightViewEndTransition(
    offsetX: Animatable<Float, AnimationVector1D>,
    requiredOffset: Float,
    coroutineScope: CoroutineScope,
    onOpen: (Boolean) -> Unit
) {
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
}

private fun sideNavigationEndTransition(
    offsetX: Animatable<Float, AnimationVector1D>,
    dragOffset: Float,
    coroutineScope: CoroutineScope,
    onOpenSideNav: (Boolean) -> Unit
) {
    if (offsetX.targetValue > dragOffset / 2) {
        coroutineScope.launch {
            offsetX.animateTo(dragOffset, animationSpec = tween(150))
            onOpenSideNav(true)
        }
    } else {
        coroutineScope.launch {
            offsetX.animateTo(0F, animationSpec = tween(150))
            onOpenSideNav(false)
        }
    }
}

private fun mainAnimateOffset(
    offsetX: Animatable<Float, AnimationVector1D>,
    dragAmount: Float,
    mainDragOffset: Float,
    change: PointerInputChange,
    coroutineScope: CoroutineScope,
    chatViewOffX: Animatable<Float, AnimationVector1D>,
    chatScreenOffset: Float
) {
    // this moves the chat view from right to left/left to right
    if (offsetX.targetValue <= 0f) {
        val summedChat = Offset(x = chatViewOffX.targetValue + dragAmount, y = 0f)
        val chatNewDragValueMain = Offset(x = summedChat.x.coerceIn(0f, chatScreenOffset), y = 0f)
        change.consumePositionChange()
        coroutineScope.launch {
            chatViewOffX.animateTo(chatNewDragValueMain.x, animationSpec = tween(50))
        }
    }

    // this moved the main view left/right
    val summedMain = Offset(x = offsetX.targetValue + dragAmount, y = 0f)
    val newDragValueMain = Offset(x = summedMain.x.coerceIn(0f, mainDragOffset), y = 0f)
    change.consumePositionChange()
    coroutineScope.launch {
        offsetX.animateTo(newDragValueMain.x, animationSpec = tween(50))
    }
}