package dev.baseio.slackclone.domain.model.message

import dev.baseio.slackclone.domain.mappers.DomainModel

data class SlackMessage(
  val uuid: String,
  val channelId:String,
  val message: String,
  val userId: String,
  val createdBy: String,
  val createdDate: Long,
  val modifiedDate: Long,
) : DomainModel()