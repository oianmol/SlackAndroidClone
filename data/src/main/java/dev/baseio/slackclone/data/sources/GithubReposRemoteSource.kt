package dev.baseio.slackclone.data.sources

import dev.baseio.slackclone.data.remote.GithubApiService
import dev.baseio.slackclone.data.remote.model.NETRepoListData
import dev.baseio.slackclone.data.remote.safeApiCall
import dev.baseio.slackclone.domain.SafeResult
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