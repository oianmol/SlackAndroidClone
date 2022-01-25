package com.mutualmobile.praxis.domain.usecases

import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.model.DOMJoke
import com.mutualmobile.praxis.domain.repository.IJokesRepo

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