package dev.baseio.slackclone.domain.repository

import androidx.paging.PagingData
import dev.baseio.slackclone.domain.model.DOMRepo
import kotlinx.coroutines.flow.Flow

interface IGithubRepo {
  suspend fun getTrendingRepos(
    query: String
  ): Flow<PagingData<DOMRepo>>
}