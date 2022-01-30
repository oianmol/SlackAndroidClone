package dev.baseio.slackclone.navigator.fragmentnavigator

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import dev.baseio.slackclone.navigator.FragmentNavigationCommand
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator
import dev.baseio.slackclone.navigator.asFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SlackCloneFragmentNavGraphNavigator @Inject constructor() : FragmentNavGraphNavigator() {

  override fun navigateFragment(destination: Int, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
    val options = optionsBuilder?.let { navOptions(it) }
    navigationCommands.tryEmit(
      FragmentNavigationCommand.NavigateToFragmentDestination(
        destination,
        options
      )
    )
  }

  override fun navigateAndClearBackStack(destination: Int) {
    navigationCommands.tryEmit(
      FragmentNavigationCommand.NavigateToFragmentDestination(
        destination,
        navOptions {
          popUpTo(0)
        })
    )
  }

  override fun popUpTo(destination: Int, inclusive: Boolean) {
    navigationCommands.tryEmit(FragmentNavigationCommand.PopUpToDestination(destination, inclusive))
  }

  override fun <T> navigateBackWithResult(key: String, result: T, destination: Int?) {
    navigationCommands.tryEmit(
      FragmentNavigationCommand.NavigateUpWithResult(
        key = key,
        result = result,
        destination = destination
      )
    )
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  override fun <T> observeResult(key: String, destination: Int?): Flow<T> {
    return navControllerFlow
      .filterNotNull()
      .flatMapLatest { navController ->
        val backStackEntry = destination?.let { navController.getBackStackEntry(it) }
          ?: navController.currentBackStackEntry

        backStackEntry?.savedStateHandle?.let { savedStateHandle ->
          savedStateHandle.getLiveData<T?>(key)
            .asFlow()
            .filter { it != null }
            .onEach {
              // Nullify the result to avoid resubmitting it
              savedStateHandle.set(key, null)
            }
        } ?: emptyFlow()
      }
  }
}