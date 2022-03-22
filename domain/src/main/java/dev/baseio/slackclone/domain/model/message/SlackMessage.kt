package dev.baseio.slackclone.domain.model.message

import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels


interface DomainLayerMessages {
  data class SlackMessage(
    val uuid: String,
    val channelId: String,
    val message: String,
    val userId: String,
    val createdBy: String,
    val createdDate: Long,
    val modifiedDate: Long,
  )

  data class LastMessage(
    val channel: DomainLayerChannels.SlackChannel,
    val message: DomainLayerMessages.SlackMessage
  )
}
