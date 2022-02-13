package dev.baseio.slackclone.data.repository

import com.github.vatbub.randomusers.Generator
import com.github.vatbub.randomusers.result.RandomUser
import dev.baseio.slackclone.common.injection.dispatcher.CoroutineDispatcherProvider
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import dev.baseio.slackclone.domain.repository.UsersRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SlackUserRepository @Inject constructor(
  private val mapper: EntityMapper<DomainLayerUsers.SlackUser, RandomUser>,
  private val coroutineMainDispatcherProvider: CoroutineDispatcherProvider
) :
  UsersRepository {
  override suspend fun getUsers(count: Int): List<DomainLayerUsers.SlackUser> {
    return withContext(coroutineMainDispatcherProvider.io) {
      Generator.generateRandomUsers(
        RandomUser.RandomUserSpec(),
        count
      ).map {
        mapper.mapToDomain(it)
      }
    }
  }
}