package dev.baseio.slackclone.data.injection

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.local.SlackDatabase
import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.dao.SlackUserDao
import dev.baseio.slackclone.data.repository.SlackLocalChannelsRepositoryImpl
import dev.baseio.slackclone.domain.repository.LocalChannelsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): SlackDatabase {
    return Room.databaseBuilder(
      context,
      SlackDatabase::class.java, "slack-db"
    ).fallbackToDestructiveMigration().build()
  }

  @Provides
  @Singleton
  fun provideChannelDao(slackDatabase: SlackDatabase): SlackChannelDao =
    slackDatabase.slackChannelDao()

  @Provides
  @Singleton
  fun provideUserDao(slackDatabase: SlackDatabase): SlackUserDao = slackDatabase.slackUserDao()
}