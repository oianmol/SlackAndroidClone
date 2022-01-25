package dev.baseio.slackclone.commonui.material

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme

@Composable
fun CommonTopAppBar(titleText:String){
  SlackCloneSurface(
    color = SlackCloneTheme.colors.uiBackground,
    contentColor = SlackCloneTheme.colors.accent,
    elevation = 4.dp
  ) {
    TopAppBar(
      title = {
        Text(
          text = titleText,
          color = SlackCloneTheme.colors.textPrimary,
          textAlign = TextAlign.Start,
        )
      },
      backgroundColor = SlackCloneTheme.colors.uiBackground,
    )
  }
}