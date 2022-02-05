package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.users.DomRandomUser
import dev.baseio.slackclone.domain.repository.UsersRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseFetchUsers(private val usersRepository: UsersRepository) :
  BaseUseCase<List<DomRandomUser>, Int> {
  override suspend fun perform(params: Int): List<DomRandomUser> {
    return usersRepository.getUsers(params)
  }
}