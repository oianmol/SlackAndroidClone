package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.model.users.DomRandomUser

interface UsersRepository {
  fun getUsers(count: Int): List<DomRandomUser>
}