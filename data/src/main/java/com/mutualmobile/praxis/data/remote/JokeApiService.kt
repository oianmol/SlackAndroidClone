package com.mutualmobile.praxis.data.remote

import com.mutualmobile.praxis.data.remote.model.NETJokeListData
import retrofit2.Retrofit
import retrofit2.http.GET

interface JokeApiService {

  companion object {
    fun createRetrofitService(retrofit: Retrofit): JokeApiService {
      return retrofit.create(JokeApiService::class.java)
    }
  }

  @GET("/jokes/random/5")
  suspend fun getFiveRandomJokes(): NETJokeListData
}