package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.users.DomainLayer
import dev.baseio.slackclone.domain.repository.UsersRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseFetchUsers(private val usersRepository: UsersRepository) :
  BaseUseCase<List<DomainLayer.Users.SlackUser>, Int> {
  override suspend fun perform(params: Int): List<DomainLayer.Users.SlackUser> {
    return usersRepository.getUsers(params)
  }
}