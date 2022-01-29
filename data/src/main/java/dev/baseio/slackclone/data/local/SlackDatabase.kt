package dev.baseio.slackclone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.dao.SlackUserDao
import dev.baseio.slackclone.data.local.model.SlackChannel
import dev.baseio.slackclone.data.local.model.SlackUser

@Database(entities = [SlackUser::class, SlackChannel::class], version = 1)
abstract class SlackDatabase : RoomDatabase() {
  abstract fun slackUserDao(): SlackUserDao
  abstract fun slackChannelDao(): SlackChannelDao
}