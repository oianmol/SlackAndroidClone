package com.mutualmobile.praxis.data.sources

import com.mutualmobile.praxis.data.remote.model.NETRepoListData
import com.mutualmobile.praxis.domain.SafeResult

interface IGithubReposRemoteSource {
  suspend fun getGithubTrendingRepos(
    query: String,
    page: Int,
    itemsPerPage: Int
  ): SafeResult<NETRepoListData>
}