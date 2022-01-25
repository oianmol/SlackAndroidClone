package com.mutualmobile.praxis.di

import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.FragmentNavGraphNavigator
import com.mutualmobile.praxis.navigator.composenavigator.PraxisComposeNavigator
import com.mutualmobile.praxis.navigator.fragmentnavigator.PraxisFragmentNavGraphNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

  @Binds
  @Singleton
  abstract fun provideComposeNavigator(praxisComposeNavigator: PraxisComposeNavigator): ComposeNavigator


  @Binds
  @Singleton
  abstract fun provideFragmentNavGraphNavigator(praxisFragmentNavGraphNavigator: PraxisFragmentNavGraphNavigator): FragmentNavGraphNavigator
}
