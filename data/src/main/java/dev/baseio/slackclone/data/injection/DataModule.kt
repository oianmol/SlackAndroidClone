package dev.baseio.slackclone.data.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.baseio.slackclone.data.local.SlackDatabase
import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import dev.baseio.slackclone.data.local.dao.SlackUserDao
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): SlackDatabase {
    return Room.inMemoryDatabaseBuilder(
      context,
      SlackDatabase::class.java,
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
  }

  @Provides
  @Singleton
  fun providesSlackMessageDao(slackDatabase: SlackDatabase): SlackMessageDao =
    slackDatabase.slackMessageDao()

  @Provides
  @Singleton
  fun provideChannelDao(slackDatabase: SlackDatabase): SlackChannelDao =
    slackDatabase.slackChannelDao()

  @Provides
  @Singleton
  fun provideUserDao(slackDatabase: SlackDatabase): SlackUserDao = slackDatabase.slackUserDao()
}