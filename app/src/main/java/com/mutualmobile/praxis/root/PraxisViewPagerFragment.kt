package com.mutualmobile.praxis.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.FragmentViewPagerBinding
import com.mutualmobile.praxis.navigator.fragmentnavigator.PraxisFragmentNavGraphNavigator
import com.mutualmobile.praxis.root.adapter.GITHUB_REPOS_PAGE_INDEX
import com.mutualmobile.praxis.root.adapter.JOKES_PAGE_INDEX
import com.mutualmobile.praxis.root.adapter.PraxisPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PraxisViewPagerFragment() : Fragment() {

  private lateinit var binding: FragmentViewPagerBinding

  @Inject
  lateinit var navigator: PraxisFragmentNavGraphNavigator

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    lifecycleScope.launchWhenCreated {
      navigator.handleNavigationCommands(findNavController())
    }
    binding = FragmentViewPagerBinding.inflate(inflater, container, false)
    val tabLayout = binding.tabs
    val viewPager = binding.viewPager

    viewPager.adapter = PraxisPagerAdapter(fragment = this)
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
      OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        if (viewPager.currentItem == 1) {
          viewPager.currentItem = 0
        } else {
          navigator.navigateUp()
        }
      }

    })
    // Set the text for each tab
    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
      tab.text = getTabTitle(position)
    }.attach()

    return binding.root
  }

  private fun getTabTitle(position: Int): String? {
    return when (position) {
      JOKES_PAGE_INDEX -> getString(R.string.jokes)
      GITHUB_REPOS_PAGE_INDEX -> getString(R.string.trending_repos)
      else -> null
    }
  }
}
