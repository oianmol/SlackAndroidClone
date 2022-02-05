package dev.baseio.slackclone.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "slackMessage")
data class DBSlackMessage(
  @PrimaryKey val uuid: String,
  @ColumnInfo(name = "channelId") val channelId: String,
  @ColumnInfo(name = "message") val message: String,
  @ColumnInfo(name = "from") val userId: String,
  @ColumnInfo(name = "createdBy") val createdBy: String,
  @ColumnInfo(name = "createdDate") val createdDate: Long,
  @ColumnInfo(name = "modifiedDate") val modifiedDate: Long,
)