package dev.baseio.slackclone.domain.model.users

data class DomRandomUser(
  val gender: String,
  val name: String,
  val location: String,
  val email: String,
  val login: String,
  val dateOfBirth: String,
  val registrationDate: String,
  val phone: String,
  val cell: String,
  val picture: String,
  val nationality: String,
)
