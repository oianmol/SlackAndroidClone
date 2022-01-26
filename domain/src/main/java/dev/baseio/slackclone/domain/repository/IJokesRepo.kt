package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.SafeResult
import dev.baseio.slackclone.domain.model.DOMJokeList

interface IJokesRepo {
    suspend fun getFiveRandomJokes(): SafeResult<DOMJokeList>
}