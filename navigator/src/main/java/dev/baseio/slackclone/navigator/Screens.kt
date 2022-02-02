package dev.baseio.slackclone.navigator

import androidx.navigation.NamedNavArgument

sealed class SlackScreen(
  private val baseRoute: String,
  val navArguments: List<NamedNavArgument> = emptyList()
) {
  val name: String = baseRoute.appendArguments(navArguments)

  // onboarding
  object GettingStarted : SlackScreen("gettingStarted")
  object SkipTypingScreen : SlackScreen("SkipTypingUI")
  object EmailAddressInputUI : SlackScreen("EmailAddressInputUI")
  object WorkspaceInputUI : SlackScreen("WorkspaceInputUI")

  // dashboard
  object Dashboard : SlackScreen("Dashboard")

}

sealed class SlackRoute(val name: String) {
  object OnBoarding : SlackRoute("onboarding")
  object Dashboard : SlackRoute("dashboard")
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