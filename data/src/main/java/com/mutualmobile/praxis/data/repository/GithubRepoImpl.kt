package com.mutualmobile.praxis.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.data.remote.model.NETRepoListData
import com.mutualmobile.praxis.data.repository.paging.GithubPagingSource
import com.mutualmobile.praxis.data.sources.IGithubReposRemoteSource
import com.mutualmobile.praxis.domain.model.DOMRepo
import com.mutualmobile.praxis.domain.model.DOMRepoList
import com.mutualmobile.praxis.domain.repository.IGithubRepo
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