package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.model.users.DomainLayerUsers

interface UsersRepository {
  suspend fun getUsers(count: Int): List<DomainLayerUsers.SlackUser>
}