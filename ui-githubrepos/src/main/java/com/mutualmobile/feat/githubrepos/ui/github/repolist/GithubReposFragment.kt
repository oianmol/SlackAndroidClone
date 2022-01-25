package com.mutualmobile.feat.githubrepos.ui.github.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.mutualmobile.feat.githubrepos.R
import com.mutualmobile.feat.githubrepos.databinding.FragmentReposBinding
import com.mutualmobile.feat.githubrepos.ui.github.repolist.adapter.ReposLoadStateAdapter
import com.mutualmobile.feat.githubrepos.ui.github.repolist.adapter.ReposPagingAdapter
import com.mutualmobile.feat.githubrepos.ui.github.repolist.adapter.ReposPagingAdapter.RepoClickListener
import com.mutualmobile.feat.githubrepos.ui.model.UIRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class GithubReposFragment : Fragment(), RepoClickListener {

  private val viewModel by viewModels<GithubReposVM>()
  private lateinit var binding: FragmentReposBinding

  private var reposPagingAdapter: ReposPagingAdapter = ReposPagingAdapter(repoClickListener = this)

  companion object {
    const val ARG_UI_REPO_MODEL = "uiRepoModel"
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentReposBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = this
    initRecyclerView()
    initListeners()
    fetchTrendingRepos()
    return binding.root
  }

  private fun initListeners() {
    viewModel.reposFlowLiveData.observe(this, { flow ->
      viewLifecycleOwner.lifecycleScope.launch {
        flow.collectLatest { pagingData ->
          reposPagingAdapter.submitData(pagingData = pagingData.map { viewModel.mapToUiRepo(it) })
        }
      }
    })

    reposPagingAdapter.addLoadStateListener { loadStates ->
      handleLoadStates(loadStates)
    }
  }

  private fun handleLoadStates(loadStates: CombinedLoadStates) {
    binding.isLoading = loadStates.refresh is LoadState.Loading
    when (val result = loadStates.refresh) {
      is Error -> {
        Timber.e(result.error)
      }
      else -> {
        // Do nothing
      }
    }
  }

  private fun initRecyclerView() {
    with(binding.rvRepoList) {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      adapter = reposPagingAdapter.withLoadStateHeaderAndFooter(
        header = ReposLoadStateAdapter(),
        footer = ReposLoadStateAdapter()
      )
    }
  }

  private fun fetchTrendingRepos() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewModel.getGitHubTrendingRepos()
    }
  }

  override fun onRepoItemClicked(uiRepo: UIRepo) {
    findNavController().navigate(
      resId = R.id.action_reposFragment_to_repoDetailsFragment,
      args = bundleOf(ARG_UI_REPO_MODEL to uiRepo)
    )
  }
}