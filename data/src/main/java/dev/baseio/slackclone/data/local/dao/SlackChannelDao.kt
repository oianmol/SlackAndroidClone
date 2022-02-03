package dev.baseio.slackclone.data.local.dao

import androidx.room.*
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import kotlinx.coroutines.flow.Flow

@Dao
interface SlackChannelDao {
  @Query("SELECT * FROM slackChannel")
  fun getAll(): List<DBSlackChannel>

  @Query("SELECT * FROM slackChannel")
  fun getAllAsFlow(): Flow<List<DBSlackChannel>>

  @Query("SELECT * FROM slackChannel WHERE uuid IN (:groupIds)")
  fun loadAllByIds(groupIds: Array<String>): List<DBSlackChannel>

  @Query(
    "SELECT * FROM slackChannel WHERE name LIKE :name"
  )
  fun findByName(name:String): List<DBSlackChannel>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll( channelDB: List<DBSlackChannel>)

  @Delete
  fun delete(channelDB: DBSlackChannel)
}