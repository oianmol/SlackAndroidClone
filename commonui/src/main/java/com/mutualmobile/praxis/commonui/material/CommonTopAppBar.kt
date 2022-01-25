package com.mutualmobile.praxis.commonui.material

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme

@Composable
fun CommonTopAppBar(titleText:String){
  PraxisSurface(
    color = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.accent,
    elevation = 4.dp
  ) {
    TopAppBar(
      title = {
        Text(
          text = titleText,
          color = PraxisTheme.colors.textPrimary,
          textAlign = TextAlign.Start,
        )
      },
      backgroundColor = PraxisTheme.colors.uiBackground,
    )
  }
}