package dev.baseio.slackclone.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.baseio.slackclone.data.local.model.DBSlackMessage

@Dao
interface SlackMessageDao {
  @Query("SELECT * FROM slackMessage")
  fun getAll(): List<DBSlackMessage>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(messages: List<DBSlackMessage>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(message: DBSlackMessage)

  // The Int type parameter tells Room to use a PositionalDataSource object.
  @Query("SELECT * FROM slackMessage where channelId = :params ORDER BY createdDate DESC")
  fun messagesByDate(params: String?): PagingSource<Int, DBSlackMessage>

  @Query("SELECT * from slackMessage where uuid like :uuid")
  fun getById(uuid: String): DBSlackMessage
}