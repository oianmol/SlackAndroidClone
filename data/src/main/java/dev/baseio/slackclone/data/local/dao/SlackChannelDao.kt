package dev.baseio.slackclone.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import kotlinx.coroutines.flow.Flow

@Dao
interface SlackChannelDao {
  @Query("SELECT * FROM slackchannel")
  fun getAll(): List<DBSlackChannel>

  @Query("SELECT * FROM slackchannel")
  fun getAllAsFlow(): Flow<List<DBSlackChannel>>

  @Query("SELECT * FROM slackchannel WHERE uuid IN (:groupIds)")
  fun loadAllByIds(groupIds: Array<String>): List<DBSlackChannel>

  @Query(
    "SELECT * FROM slackchannel WHERE name LIKE :name"
  )
  fun findByName(name:String): List<DBSlackChannel>

  @Insert
  fun insertAll( channelDB: List<DBSlackChannel>)

  @Delete
  fun delete(channelDB: DBSlackChannel)
}