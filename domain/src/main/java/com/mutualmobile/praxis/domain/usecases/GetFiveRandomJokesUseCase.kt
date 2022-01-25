package dev.baseio.slackclone.domain.usecases

import dev.baseio.slackclone.domain.SafeResult
import dev.baseio.slackclone.domain.model.DOMJoke
import dev.baseio.slackclone.domain.repository.IJokesRepo

/**
 * Created by Vipul Asri on 13/01/21.
 */

class GetFiveRandomJokesUseCase(private val jokesRepo: IJokesRepo) :
    BaseUseCase<SafeResult<List<DOMJoke>>, Unit> {

  override suspend fun perform(): SafeResult<List<DOMJoke>> {
    return when (val result = jokesRepo.getFiveRandomJokes()) {
      is SafeResult.Success -> SafeResult.Success(result.data.DOMJokes)
      is SafeResult.NetworkError -> result
      is SafeResult.Failure -> result
    }
  }

}