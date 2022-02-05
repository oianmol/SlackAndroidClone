package dev.baseio.slackclone.data.repository

import com.github.vatbub.randomusers.Generator
import com.github.vatbub.randomusers.result.RandomUser
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.domain.model.users.DomainLayer
import dev.baseio.slackclone.domain.repository.UsersRepository
import javax.inject.Inject

class SlackUserRepository @Inject constructor(private val mapper: EntityMapper<DomainLayer.Users.SlackUser, RandomUser>) :
  UsersRepository {
  override fun getUsers(count: Int): List<DomainLayer.Users.SlackUser> {
    return Generator.generateRandomUsers(
      RandomUser.RandomUserSpec(),
      count
    ).map {
      mapper.mapToDomain(it)
    }
  }
}