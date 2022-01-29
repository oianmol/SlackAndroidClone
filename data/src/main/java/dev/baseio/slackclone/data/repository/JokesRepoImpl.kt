package dev.baseio.slackclone.data.repository

import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.data.remote.model.NETJokeListData
import dev.baseio.slackclone.data.sources.IJokesRemoteSource
import dev.baseio.slackclone.domain.SafeResult
import dev.baseio.slackclone.domain.model.DOMJokeList
import java.lang.RuntimeException

/**
 * Created by Vipul Asri on 13/01/21.
 */

class JokesRepoImpl(
  private val remoteSource: IJokesRemoteSource,
  private val DOMJokeListResponseMapper: EntityMapper<DOMJokeList, NETJokeListData>
) : IJokesRepo {

  override suspend fun getFiveRandomJokes(): SafeResult<DOMJokeList> {
    return when (val result = remoteSource.getFiveRandomJokes()) {
      is SafeResult.Success<*> -> SafeResult.Success(DOMJokeListResponseMapper.mapToDomain(result.data as NETJokeListData))
      is SafeResult.Failure -> SafeResult.Failure(result.exception)
      SafeResult.NetworkError -> SafeResult.NetworkError
      else -> { throw RuntimeException()}
    }
  }

}