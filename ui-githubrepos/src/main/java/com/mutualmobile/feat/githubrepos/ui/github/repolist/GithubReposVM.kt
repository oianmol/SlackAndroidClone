package com.mutualmobile.feat.githubrepos.ui.github.repolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mutualmobile.feat.githubrepos.ui.model.UIRepo
import com.mutualmobile.feat.githubrepos.ui.model.UIRepoMapper
import com.mutualmobile.praxis.domain.model.DOMRepo
import com.mutualmobile.praxis.domain.model.request.GithubReposSearchRequest
import com.mutualmobile.praxis.domain.usecases.GetGithubTrendingReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubReposVM @Inject constructor(
  private val getGithubReposUseCase: GetGithubTrendingReposUseCase,
  private val uiRepoMapper: UIRepoMapper
) : ViewModel() {

  companion object {
    private const val DEFAULT_QUERY = "flutter"
  }

  private val currentQuery = MutableLiveData(DEFAULT_QUERY)

  private val _reposFlowLiveData = MutableLiveData<Flow<PagingData<DOMRepo>>>()
  val reposFlowLiveData: LiveData<Flow<PagingData<DOMRepo>>> = _reposFlowLiveData

  fun mapToUiRepo(domRepo: DOMRepo): UIRepo {
    return uiRepoMapper.mapToPresentation(domRepo)
  }

  fun getGitHubTrendingRepos() = viewModelScope.launch {
    _reposFlowLiveData.value = getGithubReposUseCase.perform(
      GithubReposSearchRequest(query = currentQuery.value.orEmpty())
    )
  }
}