package com.mutualmobile.feat.githubrepos.di

import com.mutualmobile.feat.githubrepos.ui.model.UIRepoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class GithubReposVMModule {

  @Provides
  fun provideUiModelMapper(): UIRepoMapper {
    return UIRepoMapper()
  }
}