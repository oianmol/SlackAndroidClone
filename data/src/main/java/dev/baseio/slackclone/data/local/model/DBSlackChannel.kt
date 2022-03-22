package dev.baseio.slackclone.data.local.model

import androidx.room.*

@Entity(tableName = "slackChannel")
data class DBSlackChannel(
  @PrimaryKey val uuid: String,
  @ColumnInfo(name = "name") val name: String?,
  @ColumnInfo(name = "createdDate") val createdDate: Long? = System.currentTimeMillis(),
  @ColumnInfo(name = "modifiedDate") val modifiedDate: Long? = System.currentTimeMillis(),
  @ColumnInfo(name = "isMuted") val isMuted: Boolean? = false,
  @ColumnInfo(name = "isStarred") val isStarred: Boolean? = false,
  @ColumnInfo(name = "isPrivate") val isPrivate: Boolean? = false,
  @ColumnInfo(name = "isShareOutSide") val isShareOutSide: Boolean? = false,
  @ColumnInfo(name = "photo") val avatarUrl: String? = null,
  @ColumnInfo(name = "isOneToOne") val isOneToOne: Boolean? = null
)

data class ChannelWithLastMessage(
  @Embedded
  val message: DBSlackMessage,
  @Relation(parentColumn = "channelId", entityColumn = "uuid") val dbSlackChannel: DBSlackChannel
)