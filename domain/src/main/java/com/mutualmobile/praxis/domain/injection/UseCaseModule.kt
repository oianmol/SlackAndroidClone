package com.mutualmobile.praxis.domain.injection

import com.mutualmobile.praxis.domain.repository.IGithubRepo
import com.mutualmobile.praxis.domain.repository.IJokesRepo
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.domain.usecases.GetGithubTrendingReposUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Vipul Asri on 13/01/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

  @Provides
  fun provideGetFiveRandomJokes(repo: IJokesRepo): GetFiveRandomJokesUseCase {
    return GetFiveRandomJokesUseCase(repo)
  }

  @Provides
  fun provideGithubTrendingRepos(repo: IGithubRepo): GetGithubTrendingReposUseCase {
    return GetGithubTrendingReposUseCase(repo)
  }
}