package dev.baseio.slackclone.domain.usecases

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.DOMRepo
import dev.baseio.slackclone.domain.model.request.GithubReposSearchRequest
import dev.baseio.slackclone.domain.repository.IGithubRepo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Pushpal Roy on 10/12/21.
 */

class GetGithubTrendingReposUseCase(private val githubRepo: IGithubRepo) :
  BaseUseCase<Flow<PagingData<DOMRepo>>, GithubReposSearchRequest> {

  override suspend fun perform(params: GithubReposSearchRequest): Flow<PagingData<DOMRepo>> {
    return githubRepo.getTrendingRepos(query = params.query)
  }
}