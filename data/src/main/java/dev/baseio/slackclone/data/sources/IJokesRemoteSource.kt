package dev.baseio.slackclone.data.sources

import dev.baseio.slackclone.data.remote.model.NETJokeListData
import dev.baseio.slackclone.domain.SafeResult

/**
 * Created by Vipul Asri on 13/01/21.
 */

interface IJokesRemoteSource {
  suspend fun getFiveRandomJokes(): SafeResult<NETJokeListData>
}