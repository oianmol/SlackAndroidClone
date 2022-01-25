package com.mutualmobile.feat.jokes.di

import com.mutualmobile.feat.jokes.ui.model.UIJokeMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class JokesVMModule {

  @Provides
  fun provideUiModelMapper(): UIJokeMapper {
    return UIJokeMapper()
  }
}