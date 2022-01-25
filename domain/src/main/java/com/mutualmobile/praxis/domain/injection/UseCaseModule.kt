package dev.baseio.slackclone.domain.injection

import dev.baseio.slackclone.domain.repository.IGithubRepo
import dev.baseio.slackclone.domain.repository.IJokesRepo
import dev.baseio.slackclone.domain.usecases.GetFiveRandomJokesUseCase
import dev.baseio.slackclone.domain.usecases.GetGithubTrendingReposUseCase
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