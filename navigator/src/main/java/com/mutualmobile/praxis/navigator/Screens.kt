package com.mutualmobile.praxis.navigator

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
  private val baseRoute: String,
  val navArguments: List<NamedNavArgument> = emptyList()
) {
  val route: String = baseRoute.appendArguments(navArguments)

  object Auth : Screen("auth")
  object ForgotPassword : Screen("forgotPassword")
  object Jokes : Screen("jokes")
  object RepoDetails : Screen("repoDetails")

  object JokeDetail : Screen(
    baseRoute = "jokeDetail",
    navArguments = listOf(navArgument("jokeId") { type = NavType.LongType })
  ) {
    fun createRoute(jokeId: String) =
      route.replace("{${navArguments.first().name}}", jokeId)
  }
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
  val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
    .orEmpty()
  val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
    .orEmpty()
  return "$this$mandatoryArguments$optionalArguments"
}