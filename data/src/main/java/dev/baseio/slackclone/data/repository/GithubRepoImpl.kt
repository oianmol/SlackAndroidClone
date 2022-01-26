package dev.baseio.slackclone.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.data.remote.model.NETRepoListData
import dev.baseio.slackclone.data.repository.paging.GithubPagingSource
import dev.baseio.slackclone.data.sources.IGithubReposRemoteSource
import dev.baseio.slackclone.domain.model.DOMRepo
import dev.baseio.slackclone.domain.model.DOMRepoList
import dev.baseio.slackclone.domain.repository.IGithubRepo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Pushpal Roy on 10/12/21.
 */

class GithubRepoImpl(
  private val remoteSource: IGithubReposRemoteSource,
  private val repoListEntityMapper: EntityMapper<DOMRepoList, NETRepoListData>
) : IGithubRepo {

  override suspend fun getTrendingRepos(
    query: String,
  ): Flow<PagingData<DOMRepo>> {
    return Pager(
      config = PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = true
      ),
      pagingSourceFactory = {
        GithubPagingSource(
          pagingRemoteSource = remoteSource,
          query = query,
          entityMapper = repoListEntityMapper
        )
      }
    ).flow
  }
}