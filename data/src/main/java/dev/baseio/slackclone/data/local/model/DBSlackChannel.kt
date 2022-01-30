package dev.baseio.slackclone.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.baseio.slackclone.data.mapper.DataModel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import java.util.*

@Entity(tableName = "slackChannel")
data class DBSlackChannel(
  @PrimaryKey val uuid: String,
  @ColumnInfo(name = "name") val name: String?,
  @ColumnInfo(name = "description") val description: String?,
  @ColumnInfo(name = "createdBy") val createdBy: String,
  @ColumnInfo(name = "createdDate") val createdDate: String,
  @ColumnInfo(name = "modifiedDate") val modifiedDate: String,
  @ColumnInfo(name = "isMuted") val isMuted: Boolean,
  @ColumnInfo(name = "isStarred") val isStarred: Boolean,
  @ColumnInfo(name = "type") val channelType: SlackChannelType,
  @ColumnInfo(name = "isPrivate") val isPrivate: Boolean
) : DataModel()
