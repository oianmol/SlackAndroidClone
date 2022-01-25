package com.mutualmobile.feat.jokes.ui.joke.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.feat.jokes.ui.joke.InMemoryDataTemp
import com.mutualmobile.feat.jokes.ui.model.UIJoke
import com.mutualmobile.feat.jokes.ui.model.UIJokeMapper
import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.model.DOMJoke
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
  private val getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase,
  private val uiJokesMapper: UIJokeMapper,
  private val navigator: ComposeNavigator
) : ViewModel() {

  var viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    private set

  init {
    loadJokes()
  }

  private fun loadJokes() {
    viewState.value = HomeViewState.Loading
    viewModelScope.launch {
      when (val result = getFiveRandomJokesUseCase.perform()) {
        is SafeResult.Success -> {
          val jokes = uiJokes(result)
          InMemoryDataTemp.jokes = jokes
          viewState.value = HomeViewState.ShowJokes(jokes)
        }
        is SafeResult.Failure -> {
          Timber.e("onError")
          viewState.value = HomeViewState.Error(result.message)
        }
        SafeResult.NetworkError -> {
          viewState.value = HomeViewState.Error("Network Error")
        }
      }
    }
  }

  private fun uiJokes(result: SafeResult.Success<List<DOMJoke>>) =
    result.data.map {
      uiJokesMapper.mapToPresentation(it)
    }

  fun showJoke(jokeId: Int) {
    navigator.navigate(Screen.JokeDetail.createRoute(jokeId.toString()))
  }
}

sealed class HomeViewState {
  object Loading : HomeViewState()
  class ShowJokes(val jokes: List<UIJoke>) : HomeViewState()
  class Error(val message: String) : HomeViewState()
}