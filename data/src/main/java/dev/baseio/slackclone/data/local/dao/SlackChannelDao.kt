package dev.baseio.slackclone.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.baseio.slackclone.data.local.model.SlackChannel
import dev.baseio.slackclone.data.local.model.SlackUser

@Dao
interface SlackChannelDao {
  @Query("SELECT * FROM slackchannel")
  fun getAll(): List<SlackChannel>

  @Query("SELECT * FROM slackchannel WHERE uuid IN (:groupIds)")
  fun loadAllByIds(groupIds: Array<String>): List<SlackChannel>

  @Query(
    "SELECT * FROM slackchannel WHERE name LIKE :name"
  )
  fun findByName(name:String): List<SlackUser>

  @Insert
  fun insertAll(vararg channel: SlackChannel)

  @Delete
  fun delete(channel: SlackChannel)
}