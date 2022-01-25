package com.mutualmobile.praxis.navigator.fragmentnavigator

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import com.mutualmobile.praxis.navigator.FragmentNavigationCommand
import com.mutualmobile.praxis.navigator.FragmentNavGraphNavigator
import com.mutualmobile.praxis.navigator.asFlow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PraxisFragmentNavGraphNavigator @Inject constructor() : FragmentNavGraphNavigator() {

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