package com.mutualmobile.feat.jokes.ui.joke.jokedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme

@Composable
fun JokeDetailsScreen(jokeDetailVM: JokeDetailVM = hiltViewModel()) {

  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier,
    topBar = {
      CommonTopAppBar("Joke Detail")
    }) {
    PraxisSurface(
      color = PraxisTheme.colors.uiBackground,
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
    ) {
      Column(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
      ) {
        val uiState by jokeDetailVM.uiState.collectAsState()
        if (uiState is JokeDetailVM.UiState.SuccessState) {
          Text(
            text = (uiState as JokeDetailVM.UiState.SuccessState).joke.joke,
            Modifier.padding(16.dp)
          )
        }
      }
    }
  }
}