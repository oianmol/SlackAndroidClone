package dev.baseio.slackclone.domain.usecases.channels

import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import dev.baseio.slackclone.domain.repository.UsersRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase

class UseCaseFetchUsers(private val usersRepository: UsersRepository) :
  BaseUseCase<List<DomainLayerUsers.SlackUser>, Int> {
  override suspend fun perform(params: Int): List<DomainLayerUsers.SlackUser> {
    return usersRepository.getUsers(params)
  }
}