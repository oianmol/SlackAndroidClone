package com.mutualmobile.praxis.root.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mutualmobile.feat.githubrepos.ui.github.repolist.GithubReposFragment
import com.mutualmobile.feat.jokes.ui.joke.JokeFragment

const val JOKES_PAGE_INDEX = 0
const val GITHUB_REPOS_PAGE_INDEX = 1

class PraxisPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

  /**
   * Mapping of the ViewPager page indexes to their respective Fragments
   */
  private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
    JOKES_PAGE_INDEX to { JokeFragment() },
    GITHUB_REPOS_PAGE_INDEX to { GithubReposFragment() }
  )

  override fun getItemCount() = tabFragmentsCreators.size

  override fun createFragment(position: Int): Fragment {
    return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
  }
}
