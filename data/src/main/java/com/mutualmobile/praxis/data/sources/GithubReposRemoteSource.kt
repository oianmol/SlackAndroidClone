package com.mutualmobile.praxis.data.sources

import com.mutualmobile.praxis.data.remote.GithubApiService
import com.mutualmobile.praxis.data.remote.model.NETRepoListData
import com.mutualmobile.praxis.data.remote.safeApiCall
import com.mutualmobile.praxis.domain.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Pushpal Roy on 10/12/21.
 */

class GithubReposRemoteSource(
  private val githubApiService: GithubApiService,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IGithubReposRemoteSource {

  override suspend fun getGithubTrendingRepos(
    query: String,
    page: Int,
    itemsPerPage: Int
  ): SafeResult<NETRepoListData> {
    return safeApiCall(dispatcher) {
      githubApiService.getTrendingRepos(query, page, itemsPerPage)
    }
  }
}