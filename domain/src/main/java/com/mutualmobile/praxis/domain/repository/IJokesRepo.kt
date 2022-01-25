package com.mutualmobile.praxis.domain.repository

import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.model.DOMJokeList

interface IJokesRepo {
    suspend fun getFiveRandomJokes(): SafeResult<DOMJokeList>
}