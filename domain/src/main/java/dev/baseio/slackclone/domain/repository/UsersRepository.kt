package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.model.users.DomainLayer

interface UsersRepository {
  fun getUsers(count: Int): List<DomainLayer.Users.SlackUser>
}