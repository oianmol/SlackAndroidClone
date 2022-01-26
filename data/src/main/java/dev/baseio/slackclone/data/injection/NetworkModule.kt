package dev.baseio.slackclone.data.injection

import dev.baseio.slackclone.data.AppConstants
import dev.baseio.slackclone.data.injection.qualifiers.GitHubRetrofitClient
import dev.baseio.slackclone.data.injection.qualifiers.JokesRetrofitClient
import dev.baseio.slackclone.data.remote.GithubApiService
import dev.baseio.slackclone.data.remote.JokeApiService
import dev.baseio.slackclone.data.remote.RetrofitHelper
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