package dev.baseio.slackclone.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator
import dagger.hilt.android.AndroidEntryPoint
import dev.baseio.slackclone.onboarding.nav.OnboardingNavGraph
import javax.inject.Inject

/**
 * A fragment representing a single Auth screen.
 */
@AndroidEntryPoint
class OnboardingScreens : Fragment() {

  @Inject
  lateinit var navigatorFragment: FragmentNavGraphNavigator

  @Inject
  lateinit var composeNavigator: ComposeNavigator

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) = ComposeView(requireContext()).apply {

    // Dispose of the Composition when the view's LifecycleOwner is destroyed
    setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)

    setContent {
      // Create a Compose MaterialTheme inheriting the existing colors, typography
      // and shapes of the current View system's theme
      SlackCloneTheme {
        LaunchedEffect(Unit) {
          navigatorFragment.handleNavigationCommands(findNavController())
        }
        /**
         * Make the bridge between Compose and the fragment-based Navigation component
         * by finding the NavController and navigating to the destination:
         */
        OnboardingNavGraph(
          navigator = composeNavigator
        )
      }
    }
  }
}
