package dev.baseio.slackclone.navigator

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class Navigator {
  val navigationCommands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

  // We use a StateFlow here to allow ViewModels to start observing navigation results before the initial composition,
  // and still get the navigation result later
  val navControllerFlow = MutableStateFlow<NavController?>(null)

  fun navigateUp() {
    navigationCommands.tryEmit(NavigationCommand.NavigateUp)
  }

}

abstract class ComposeNavigator : Navigator() {
  abstract fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)? = null)
  abstract fun <T> observeResult(key: String, route: String? = null): Flow<T>
  abstract fun <T> navigateBackWithResult(key: String, result: T, route: String?)

  abstract fun popUpTo(route: String, inclusive: Boolean)
  abstract fun navigateAndClearBackStack(route: String)

  suspend fun handleNavigationCommands(navController: NavController) {
    navigationCommands
      .onSubscription { this@ComposeNavigator.navControllerFlow.value = navController }
      .onCompletion { this@ComposeNavigator.navControllerFlow.value = null }
      .collect { navController.handleComposeNavigationCommand(it) }
  }

  private fun NavController.handleComposeNavigationCommand(navigationCommand: NavigationCommand) {
    when (navigationCommand) {
      is ComposeNavigationCommand.NavigateToRoute -> {
        navigate(navigationCommand.route, navigationCommand.options)
      }
      NavigationCommand.NavigateUp -> navigateUp()
      is ComposeNavigationCommand.PopUpToRoute -> popBackStack(
        navigationCommand.route,
        navigationCommand.inclusive
      )
      is ComposeNavigationCommand.NavigateUpWithResult<*> -> {
        navUpWithResult(navigationCommand)
      }
      else -> {
        throw RuntimeException("can't handle this with ComposeNavigator")
      }
    }
  }

  private fun NavController.navUpWithResult(navigationCommand: ComposeNavigationCommand.NavigateUpWithResult<*>) {
    val backStackEntry =
      navigationCommand.route?.let { getBackStackEntry(it) }
        ?: previousBackStackEntry
    backStackEntry?.savedStateHandle?.set(
      navigationCommand.key,
      navigationCommand.result
    )

    navigationCommand.route?.let {
      popBackStack(it, false)
    } ?: run {
      navigateUp()
    }
  }
}


@OptIn(DelicateCoroutinesApi::class)
fun <T> LiveData<T>.asFlow(): Flow<T> = flow {
  val channel = Channel<T>(Channel.CONFLATED)
  val observer = Observer<T> {
    channel.trySend(it)
  }
  withContext(Dispatchers.Main.immediate) {
    observeForever(observer)
  }
  try {
    for (value in channel) {
      emit(value)
    }
  } finally {
    GlobalScope.launch(Dispatchers.Main.immediate) {
      removeObserver(observer)
    }
  }
}