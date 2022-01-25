package com.mutualmobile.praxis.data.repository

import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.data.remote.model.NETJokeListData
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.model.DOMJokeList
import com.mutualmobile.praxis.domain.repository.IJokesRepo
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