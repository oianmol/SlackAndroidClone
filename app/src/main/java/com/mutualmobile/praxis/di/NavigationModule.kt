package dev.baseio.slackclone.di

import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator
import dev.baseio.slackclone.navigator.composenavigator.SlackCloneComposeNavigator
import dev.baseio.slackclone.navigator.fragmentnavigator.SlackCloneFragmentNavGraphNavigator
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
  abstract fun provideComposeNavigator(praxisComposeNavigator: SlackCloneComposeNavigator): ComposeNavigator


  @Binds
  @Singleton
  abstract fun provideFragmentNavGraphNavigator(praxisFragmentNavGraphNavigator: SlackCloneFragmentNavGraphNavigator): FragmentNavGraphNavigator
}
