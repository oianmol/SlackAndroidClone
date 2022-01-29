package dev.baseio.slackclone.commonui.reusable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation

@Composable
fun SlackImageBox(modifier: Modifier, imageUrl: String) {
  Image(
    painter = rememberImagePainter(
      data = imageUrl,
      builder = {
        transformations(RoundedCornersTransformation(12.0f, 12.0f, 12.0f, 12.0f))
      }
    ),
    contentDescription = null,
    modifier = modifier
  )
}