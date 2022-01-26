package dev.baseio.slackclone.data.injection

import dev.baseio.slackclone.data.remote.GithubApiService
import dev.baseio.slackclone.data.remote.JokeApiService
import dev.baseio.slackclone.data.sources.GithubReposRemoteSource
import dev.baseio.slackclone.data.sources.IGithubReposRemoteSource
import dev.baseio.slackclone.data.sources.IJokesRemoteSource
import dev.baseio.slackclone.data.sources.JokesRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Vipul Asri on 13/01/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

  @Provides
  fun provideJokesNetworkSource(apiService: JokeApiService): IJokesRemoteSource {
    return JokesRemoteSource(
      apiService
    )
  }

  @Provides
  fun provideGithubNetworkSource(apiService: GithubApiService): IGithubReposRemoteSource {
    return GithubReposRemoteSource(
      apiService
    )
  }
}