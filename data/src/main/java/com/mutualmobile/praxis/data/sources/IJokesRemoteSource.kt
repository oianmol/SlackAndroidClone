package com.mutualmobile.praxis.data.sources

import com.mutualmobile.praxis.data.remote.model.NETJokeListData
import com.mutualmobile.praxis.domain.SafeResult

/**
 * Created by Vipul Asri on 13/01/21.
 */

interface IJokesRemoteSource {
  suspend fun getFiveRandomJokes(): SafeResult<NETJokeListData>
}