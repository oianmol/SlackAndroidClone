package com.mutualmobile.feat.jokes.ui.joke.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mutualmobile.feat.jokes.ui.model.UIJoke
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.commonui.theme.PraxisTypography

@Composable
fun Dashboard(homeVM: HomeVM = hiltViewModel()) {
  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier,
    topBar = {
      CommonTopAppBar("Chuck Norris Random Joke Generator")
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
        val state by homeVM.viewState.collectAsState()
        when (state) {
          is HomeViewState.Loading -> {
            LinearProgressIndicator(
              color = PraxisTheme.colors.accent, modifier = Modifier
                .fillMaxWidth()
            )
          }
          is HomeViewState.ShowJokes -> {
            val jokes = (state as HomeViewState.ShowJokes).jokes
            JokesList(jokes, homeVM)

          }
          is HomeViewState.Error -> {
            Text(text = "Error")
          }
        }
      }
    }
  }


}

@Composable
private fun JokesList(
  jokes: List<UIJoke>,
  homeVM: HomeVM
) {
  LazyColumn {
    items(jokes.size) { item ->
      ClickableText(text = buildAnnotatedString {
        withStyle(
          style = SpanStyle(
            color = PraxisTheme.colors.textPrimary,
            fontSize = PraxisTypography.body1.fontSize
          )
        ) {
          append(jokes[item].joke)
        }

      }, onClick = {
        homeVM.showJoke(jokes[item].jokeId)
      }, modifier = Modifier.padding(16.dp))
    }
  }
}