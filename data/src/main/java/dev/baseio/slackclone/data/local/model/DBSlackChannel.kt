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
  @ColumnInfo(name = "createdDate") val createdDate: Long?,
  @ColumnInfo(name = "modifiedDate") val modifiedDate: Long?,
  @ColumnInfo(name = "isMuted") val isMuted: Boolean? = false,
  @ColumnInfo(name = "isStarred") val isStarred: Boolean? = false,
  @ColumnInfo(name = "type") val channelType: SlackChannelType? = SlackChannelType.GROUP,
  @ColumnInfo(name = "isPrivate") val isPrivate: Boolean? = false,
  @ColumnInfo(name = "isShareOutSide") val isShareOutSide: Boolean? = false
) : DataModel()
