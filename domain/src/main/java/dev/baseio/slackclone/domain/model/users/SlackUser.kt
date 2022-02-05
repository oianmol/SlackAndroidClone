package dev.baseio.slackclone.domain.model.users

interface DomainLayerUsers {
  data class SlackUser(
    val gender: String,
    val name: String,
    val location: String,
    val email: String,
    val login: String,
    val dateOfBirth: Long,
    val registrationDate: Long,
    val phone: String,
    val cell: String,
    val picture: String,
    val nationality: String,
  )
}
