package dev.baseio.slackclone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.baseio.slackclone.data.local.dao.SlackChannelDao
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import dev.baseio.slackclone.data.local.dao.SlackUserDao
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import dev.baseio.slackclone.data.local.model.DBSlackMessage
import dev.baseio.slackclone.data.local.model.DBSlackUser
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import java.util.*


@Database(
    entities = [DBSlackUser::class, DBSlackChannel::class, DBSlackMessage::class],
    version = 1
)
abstract class SlackDatabase : RoomDatabase() {
    abstract fun slackUserDao(): SlackUserDao
    abstract fun slackChannelDao(): SlackChannelDao
    abstract fun slackMessageDao(): SlackMessageDao
}