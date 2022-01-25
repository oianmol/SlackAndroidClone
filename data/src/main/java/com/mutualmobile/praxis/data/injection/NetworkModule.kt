package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.AppConstants
import com.mutualmobile.praxis.data.injection.qualifiers.GitHubRetrofitClient
import com.mutualmobile.praxis.data.injection.qualifiers.JokesRetrofitClient
import com.mutualmobile.praxis.data.remote.GithubApiService
import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.remote.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Vipul Asri on 13/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  fun provideHttpClient(): OkHttpClient {
    return RetrofitHelper.createOkHttpClient()
  }

  @Provides
  @JokesRetrofitClient
  fun provideJokesRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, AppConstants.JOKES_BASE_URL)
  }

  @Provides
  @GitHubRetrofitClient
  fun provideGithubRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, AppConstants.GITHUB_BASE_URL)
  }

  @Provides
  fun provideJokesApiService(@JokesRetrofitClient retrofit: Retrofit): JokeApiService {
    return JokeApiService.createRetrofitService(retrofit)
  }

  @Provides
  fun provideGithubApiService(@GitHubRetrofitClient retrofit: Retrofit): GithubApiService {
    return GithubApiService.createRetrofitService(retrofit)
  }
}