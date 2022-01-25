package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.remote.model.RepoListResponseMapper
import com.mutualmobile.praxis.data.remote.model.JokesListResponseMapper
import com.mutualmobile.praxis.data.repository.GithubRepoImpl
import com.mutualmobile.praxis.data.repository.JokesRepoImpl
import com.mutualmobile.praxis.data.sources.IGithubReposRemoteSource
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.domain.repository.IGithubRepo
import com.mutualmobile.praxis.domain.repository.IJokesRepo
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