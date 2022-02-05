package dev.baseio.slackclone.data.mapper

import com.github.vatbub.randomusers.result.Name
import com.github.vatbub.randomusers.result.RandomUser
import dev.baseio.slackclone.domain.model.users.DomainLayer
import javax.inject.Inject

class SlackUserDataDomainMapper @Inject constructor() : EntityMapper<DomainLayer.Users.SlackUser, RandomUser> {
  override fun mapToDomain(entity: RandomUser): DomainLayer.Users.SlackUser {
    return DomainLayer.Users.SlackUser(
      entity.gender.genderText,
      entity.name.fullName(),
      entity.location.city,
      entity.email,
      entity.login.username,
      entity.dateOfBirth.toString(),
      entity.registrationDate.toString(),
      entity.phone,
      entity.cell,
      entity.picture.mediumPicture.toURI().toString(),
      entity.nationality.shortCode
    )
  }

  override fun mapToData(model: DomainLayer.Users.SlackUser): RandomUser {
    TODO("not needed!")
  }
}

private fun Name.fullName(): String {
  return "${this.firstName} ${this.lastName}"
}
