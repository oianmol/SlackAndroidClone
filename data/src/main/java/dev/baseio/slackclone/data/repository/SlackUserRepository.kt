package dev.baseio.slackclone.data.repository

import com.github.vatbub.randomusers.Generator
import com.github.vatbub.randomusers.result.RandomUser
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.users.DomRandomUser
import dev.baseio.slackclone.domain.repository.UsersRepository
import javax.inject.Inject

class SlackUserRepository @Inject constructor(private val mapper: EntityMapper<DomRandomUser, RandomUser>) :
  UsersRepository {
  override fun getUsers(count: Int): List<DomRandomUser> {
    return Generator.generateRandomUsers(
      RandomUser.RandomUserSpec(),
      count
    ).map {
      mapper.mapToDomain(it)
    }
  }
}