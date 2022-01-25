package dev.baseio.slackclone.data.injection

import dev.baseio.slackclone.data.remote.model.RepoListResponseMapper
import dev.baseio.slackclone.data.remote.model.JokesListResponseMapper
import dev.baseio.slackclone.data.repository.GithubRepoImpl
import dev.baseio.slackclone.data.repository.JokesRepoImpl
import dev.baseio.slackclone.data.sources.IGithubReposRemoteSource
import dev.baseio.slackclone.data.sources.IJokesRemoteSource
import dev.baseio.slackclone.domain.repository.IGithubRepo
import dev.baseio.slackclone.domain.repository.IJokesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Vipul Asri on 13/01/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  fun provideJokesRepository(networkSource: IJokesRemoteSource): IJokesRepo {
    return JokesRepoImpl(networkSource, JokesListResponseMapper())
  }

  @Provides
  fun provideGithubReposRepository(networkSource: IGithubReposRemoteSource): IGithubRepo {
    return GithubRepoImpl(networkSource, RepoListResponseMapper())
  }
}