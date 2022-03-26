package dev.baseio.slackclone.uionboarding.compose

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import kotlinx.coroutines.delay


val loaderYellow = Color(226, 179, 75)
val loaderPinkish = Color(206, 54, 92)
val loaderBluish = Color(100, 193, 234)
val loaderGreen = Color(91, 178, 128)
val slackWhite = Color(255, 255, 255)

@Composable
fun SlackAnimation(onComplete: () -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {

    var isStartAnimation by remember {
      mutableStateOf(false)
    }

    val animatedRotateLogo by animateFloatAsState(
      targetValue = if (isStartAnimation) 380f else 40f,
      tween(durationMillis = SlackAnimSpec.ANIM_DURATION)
    )

    val animatedMoveLogo by animateDpAsState(
      targetValue = if (isStartAnimation) 0.dp else (-120).dp,
      tween(SlackAnimSpec.ANIM_DURATION)
    )

    LaunchedEffect(key1 = true, block = {
      isStartAnimation = !isStartAnimation
      delay(SlackAnimSpec.ANIM_DURATION.toLong().plus(250))
      onComplete.invoke()
    })


    SKTextLoader(Modifier.align(Alignment.Center))

    SKFourColorLoader(Modifier.align(Alignment.Center), animatedRotateLogo, animatedMoveLogo)

  }

}

@Composable
private fun SKFourColorLoader(modifier: Modifier, animatedRotateLogo: Float, animatedMoveLogo: Dp) {

  Box(
    modifier = modifier
      .offset(x = animatedMoveLogo)
      .rotate(animatedRotateLogo)
  ) {

    SlackAnimSpec.blocks.forEach { block ->
      CircularRectBlock(
        block
      )
    }
  }
}


data class CircularRectBlockData(
  val rectBlockWidth: Dp = 32.dp,
  val rectBlockHeight: Dp = 8.dp,
  val roundedCornerPercentage: Int = 30,
  val color: Color,
  val rotation: Int,
  var offsetX: Dp = 0.dp,
  var offsetY: Dp = 0.dp,
  var dropX: Dp = 0.dp,
  var dropY: Dp = 0.dp,
  val dropRotation: Int,
  val dropSize: Dp = 16.dp,
  val dropScaleDelay: Int
)


@Composable
fun CircularRectBlock(block: CircularRectBlockData) {

  var isStart by remember {
    mutableStateOf(true)
  }

  val dropSize by animateFloatAsState(
    targetValue = if (isStart) 1f else 0f,
    SlackAnimSpec.dropSizeKeyFrames(block.dropScaleDelay)
  )

  val animatedWidth by animateDpAsState(
    targetValue = if (isStart) block.rectBlockWidth else block.rectBlockHeight,
    tween(durationMillis = SlackAnimSpec.ANIM_DURATION.times(2))
  )

  LaunchedEffect(key1 = Unit, block = {
    isStart = !isStart
  })


  Droplet(block, dropSize)

  Box(
    modifier = Modifier
      .offset(block.offsetX, block.offsetY)
      .rotate(block.dropRotation.toFloat())
      .width(animatedWidth) // change this to animatedWidth later
      .height(block.rectBlockHeight)
      .background(block.color, RoundedCornerShape(block.roundedCornerPercentage))
  )
}

@Composable
fun Droplet(block: CircularRectBlockData, dropSize: Float) {
  Box(
    Modifier
      .offset(block.dropX, block.dropY)
      .rotate(block.dropRotation.plus(180f))
      .size(block.dropSize)
      .scale(dropSize)
      .background(
        block.color,
        RoundedCornerShape(
          topStartPercent = block.roundedCornerPercentage,
          topEndPercent = block.roundedCornerPercentage,
          bottomStartPercent = block.roundedCornerPercentage, bottomEndPercent = 0
        )
      )
  ) {

  }
}

@Composable
private fun SKTextLoader(modifier: Modifier) {
  var isStart by remember {
    mutableStateOf(true)
  }

  LaunchedEffect(key1 = Unit, block = {
    isStart = !isStart
  })



  Box(
    modifier = modifier.offset(x = 40.dp)
  ) {
    Row {
      AnimatedLetter("s", isStart, delay = SlackAnimSpec.ANIM_DURATION.times(0.2).toInt())
      AnimatedLetter("l", isStart, delay = SlackAnimSpec.ANIM_DURATION.times(0.4).toInt())
      AnimatedLetter("a", isStart, delay = SlackAnimSpec.ANIM_DURATION.times(0.6).toInt())
      AnimatedLetter("c", isStart, delay = SlackAnimSpec.ANIM_DURATION.times(0.8).toInt())
      AnimatedLetter("k", isStart, delay = SlackAnimSpec.ANIM_DURATION)
    }
  }
}

@Composable
private fun AnimatedLetter(letter: String, isStart: Boolean, delay: Int) {
  val animatedAlpha by animateFloatAsState(
    targetValue = if (isStart) 1f else 0f,
    tween(durationMillis = SlackAnimSpec.ANIM_DURATION.div(2), delayMillis = delay)
  )
  Text(
    text = letter,
    fontWeight = FontWeight.Bold,
    style = slackTextStyle(),
    modifier = Modifier.alpha(animatedAlpha)
  )
}

@Composable
private fun slackTextStyle() = SlackCloneTypography.h2.copy(
  color = slackWhite, letterSpacing = 4.sp
)

object SlackAnimSpec {
  const val ANIM_DURATION = 1500

  fun dropSizeKeyFrames(scaleDelay: Int): AnimationSpec<Float> {
    return keyframes {
      durationMillis = ANIM_DURATION
      delayMillis = scaleDelay
      1f at 0 with LinearEasing
      1.3f at ANIM_DURATION.div(2) with LinearEasing
      0f at ANIM_DURATION with LinearEasing
    }
  }

  val blocks = mutableListOf<CircularRectBlockData>().apply {

    add(
      CircularRectBlockData(
        rectBlockWidth = 34.dp,
        rectBlockHeight = 16.dp,
        roundedCornerPercentage = 50,
        color = loaderYellow,
        rotation = 0,
        offsetY = 10.dp,
        offsetX = 16.dp,
        dropY = 32.dp,
        dropX = 16.dp,
        dropRotation = 0, dropScaleDelay = 300
      )
    )

    add(
      CircularRectBlockData(
        rectBlockWidth = 34.dp,
        rectBlockHeight = 16.dp,
        roundedCornerPercentage = 50,
        color = loaderPinkish,
        rotation = 90,
        offsetX = (-14).dp,
        offsetY = 20.dp,
        dropX = (-24).dp,
        dropY = (10).dp,
        dropRotation = 90, dropScaleDelay = 200
      )
    )
    add(
      CircularRectBlockData(
        rectBlockWidth = 34.dp,
        rectBlockHeight = 16.dp,
        roundedCornerPercentage = 50,
        color = loaderBluish,
        rotation = 180,
        offsetX = (-24).dp,
        offsetY = (-12).dp,
        dropX = (-10).dp,
        dropY = (-36).dp,
        dropRotation = 180, dropScaleDelay = 0
      )
    )

    add(
      CircularRectBlockData(
        rectBlockWidth = 34.dp,
        rectBlockHeight = 16.dp,
        roundedCornerPercentage = 50,
        color = loaderGreen,
        rotation = 270,
        offsetX = 6.dp,
        offsetY = (-24).dp,
        dropX = (36).dp,
        dropY = (-16).dp,
        dropRotation = 270, dropScaleDelay = 100
      )
    )
  }
}