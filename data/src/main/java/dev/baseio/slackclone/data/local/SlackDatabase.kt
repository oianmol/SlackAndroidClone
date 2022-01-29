package dev.baseio.slackclone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.baseio.slackclone.data.local.model.SlackUser

@Database(entities = [SlackUser::class], version = 1)
abstract class SlackDatabase : RoomDatabase() {
  abstract fun slackUserDao(): SlackUserDao
}