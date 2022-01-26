package dev.baseio.slackclone.data.sources

import dev.baseio.slackclone.data.remote.JokeApiService
import dev.baseio.slackclone.data.remote.model.NETJokeListData
import dev.baseio.slackclone.data.remote.safeApiCall
import dev.baseio.slackclone.domain.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Vipul Asri on 13/01/21.
 */

class JokesRemoteSource(
  private val jokeApiService: JokeApiService,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IJokesRemoteSource {

  override suspend fun getFiveRandomJokes(): SafeResult<NETJokeListData> {
    return safeApiCall(dispatcher) {
      jokeApiService.getFiveRandomJokes()
    }
  }
}