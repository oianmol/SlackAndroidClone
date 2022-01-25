package com.mutualmobile.praxis.navigator

import androidx.navigation.NavOptions

sealed class NavigationCommand {
  object NavigateUp : NavigationCommand()
}

sealed class ComposeNavigationCommand : NavigationCommand() {
  data class NavigateToRoute(val route: String, val options: NavOptions? = null) :
    ComposeNavigationCommand()

  data class NavigateUpWithResult<T>(
    val key: String,
    val result: T,
    val destination: String? = null
  ) : ComposeNavigationCommand()

  data class PopUpToRoute(val route: String, val inclusive: Boolean) : ComposeNavigationCommand()
}

sealed class FragmentNavigationCommand : NavigationCommand() {
  data class NavigateToFragmentDestination(val destination: Int, val options: NavOptions? = null) : FragmentNavigationCommand()
  data class NavigateUpWithResult<T>(
    val key: String,
    val result: T,
    val destination: Int? = null
  ) : FragmentNavigationCommand()

  data class PopUpToDestination(val destination: Int, val inclusive: Boolean) : FragmentNavigationCommand()

}
