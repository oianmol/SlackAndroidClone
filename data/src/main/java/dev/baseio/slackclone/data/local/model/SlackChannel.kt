package dev.baseio.slackclone.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class SlackChannel(
  @PrimaryKey val uuid: String,
  @ColumnInfo(name = "name") val firstName: String?,
  @ColumnInfo(name = "description") val description: String?,
  @ColumnInfo(name = "createdBy") val createdBy: String,
  @ColumnInfo(name = "createdDate") val createdDate: Date,
  @ColumnInfo(name = "modifiedDate") val modifiedDate: Date,
  @ColumnInfo(name = "isMuted") val isMuted: Boolean,
  @ColumnInfo(name = "isStarred") val isStarred: Boolean
)
