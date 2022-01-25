package dev.baseio.slackclone.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.data.remote.model.NETRepoListData
import dev.baseio.slackclone.data.sources.IGithubReposRemoteSource
import dev.baseio.slackclone.domain.SafeResult.NetworkError
import dev.baseio.slackclone.domain.SafeResult.Success
import dev.baseio.slackclone.domain.getErrorOrNull
import dev.baseio.slackclone.domain.getSuccessOrNull
import dev.baseio.slackclone.domain.model.DOMRepo
import dev.baseio.slackclone.domain.model.DOMRepoList
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class GithubPagingSource(
  private val pagingRemoteSource: IGithubReposRemoteSource,
  private val query: String,
  private val entityMapper: EntityMapper<DOMRepoList, NETRepoListData>
) : PagingSource<Int, DOMRepo>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DOMRepo> {
    val position = params.key ?: STARTING_PAGE_INDEX

    return try {
      when (val result = pagingRemoteSource
        .getGithubTrendingRepos(
          query = query,
          page = position,
          itemsPerPage = params.loadSize
        )) {

        is Success -> {
          val paginatedContent = result.getSuccessOrNull() ?: return LoadResult.Error(Exception())
          val mappedPaginatedContent = entityMapper.mapToDomain(paginatedContent)
          LoadResult.Page(
            data = mappedPaginatedContent.domRepos,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (paginatedContent.repos.isEmpty()) null else position + 1
          )
        }

        is NetworkError -> {
          LoadResult.Error(IOException())
        }

        else -> {
          LoadResult.Error(result.getErrorOrNull()?.exception ?: Exception())
        }
      }
    } catch (exception: IOException) {
      LoadResult.Error(exception)
    } catch (exception: HttpException) {
      LoadResult.Error(exception)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, DOMRepo>): Int? = null
}