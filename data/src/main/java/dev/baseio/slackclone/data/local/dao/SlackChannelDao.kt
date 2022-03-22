package dev.baseio.slackclone.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import dev.baseio.slackclone.data.local.model.ChannelWithLastMessage
import dev.baseio.slackclone.data.local.model.DBSlackChannel
import kotlinx.coroutines.flow.Flow

@Dao
interface SlackChannelDao {

  @Query("SELECT COUNT(*) from slackChannel ")
  fun count(): Int

  @Query("SELECT * FROM slackChannel")
  fun getAll(): List<DBSlackChannel>

  @Query("SELECT * FROM slackChannel")
  fun getAllAsFlow(): Flow<List<DBSlackChannel>>

  @Query("SELECT * FROM slackChannel WHERE uuid IN (:groupIds)")
  fun loadAllByIds(groupIds: Array<String>): List<DBSlackChannel>

  @Query(
    "SELECT * FROM slackChannel WHERE name LIKE :name"
  )
  fun findByName(name: String): List<DBSlackChannel>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(channelDB: List<DBSlackChannel>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(channel: DBSlackChannel)

  @Delete
  fun delete(channelDB: DBSlackChannel)

  @Query("SELECT * from slackChannel where uuid like :uuid")
  fun getById(uuid: String): DBSlackChannel?

  // The Int type parameter tells Room to use a PositionalDataSource object.
  @Query("SELECT * FROM slackChannel where name like '%' || :params || '%' ORDER BY name ASC")
  fun channelsByName(params: String?): PagingSource<Int, DBSlackChannel>

  // The Int type parameter tells Room to use a PositionalDataSource object.
  @Query("SELECT * FROM slackChannel ORDER BY name ASC")
  fun channelsByName(): PagingSource<Int, DBSlackChannel>

  @Query("SELECT * FROM slackmessage AS channelMessage " +
      "JOIN (SELECT channelId, max(modifiedDate) AS received_at FROM slackMessage GROUP BY channelId) AS channelMessage_last " +
      "ON channelMessage_last.channelId = channelMessage.channelId AND channelMessage_last.received_at = channelMessage.modifiedDate")
  fun getChannelsWithLastMessage(): PagingSource<Int, ChannelWithLastMessage>
}