package com.mutualmobile.praxis.data.remote

import com.mutualmobile.praxis.data.remote.model.NETRepoListData
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

  companion object {
    fun createRetrofitService(retrofit: Retrofit): GithubApiService {
      return retrofit.create(GithubApiService::class.java)
    }
  }

  @GET("search/repositories?sort=stars")
  suspend fun getTrendingRepos(
    @Query("q") query: String,
    @Query("page") page: Int,
    @Query("per_page") itemsPerPage: Int
  ): NETRepoListData
}