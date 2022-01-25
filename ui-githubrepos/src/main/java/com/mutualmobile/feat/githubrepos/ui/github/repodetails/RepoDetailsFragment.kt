package com.mutualmobile.feat.githubrepos.ui.github.repodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import com.mutualmobile.feat.githubrepos.nav.GithubNavGraph
import com.mutualmobile.feat.githubrepos.ui.github.repolist.GithubReposFragment
import com.mutualmobile.feat.githubrepos.ui.model.UIRepo
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a single Repo detail screen.
 */
@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {

  @Inject
  lateinit var navigator: ComposeNavigator

  /**
   * Include a ComposeView directly in a fragment if your full screen is built with Compose,
   * which lets you avoid using an XML layout file entirely.
   */
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) = ComposeView(requireContext()).apply {

    // Dispose of the Composition when the view's LifecycleOwner is destroyed
    // DisposeOnViewTreeLifecycleDestroyed: ViewCompositionStrategy that disposes the composition
    // when the ViewTreeLifecycleOwner of the next window the view is attached to is destroyed.
    setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)

    setContent {
      // Create a Compose MaterialTheme inheriting the existing colors, typography
      // and shapes of the current View system's theme
      PraxisTheme {
        GithubNavGraph(
          navigator = navigator,
          uiRepo = arguments?.getParcelable(GithubReposFragment.ARG_UI_REPO_MODEL) as UIRepo?
        )
      }
    }
  }
}
