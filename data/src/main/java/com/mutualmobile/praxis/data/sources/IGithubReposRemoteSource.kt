package dev.baseio.slackclone.data.sources

import dev.baseio.slackclone.data.remote.model.NETRepoListData
import dev.baseio.slackclone.domain.SafeResult

interface IGithubReposRemoteSource {
  suspend fun getGithubTrendingRepos(
    query: String,
    page: Int,
    itemsPerPage: Int
  ): SafeResult<NETRepoListData>
}