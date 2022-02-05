package dev.baseio.slackclone.data.mapper

import com.github.vatbub.randomusers.result.Name
import com.github.vatbub.randomusers.result.RandomUser
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
import javax.inject.Inject

class SlackUserMapper @Inject constructor() : EntityMapper<DomainLayerUsers.SlackUser, RandomUser> {
  override fun mapToDomain(entity: RandomUser): DomainLayerUsers.SlackUser {
    return DomainLayerUsers.SlackUser(
      entity.gender.genderText,
      entity.name.fullName(),
      entity.location.city,
      entity.email,
      entity.login.username,
      entity.dateOfBirth.time,
      entity.registrationDate.time,
      entity.phone,
      entity.cell,
      entity.picture.mediumPicture.toURI().toString(),
      entity.nationality.shortCode
    )
  }

  override fun mapToData(model: DomainLayerUsers.SlackUser): RandomUser {
    TODO("not needed!")
  }
}

private fun Name.fullName(): String {
  return "${this.firstName} ${this.lastName}"
}
