package dev.baseio.slackclone.domain.model.channel

import dev.baseio.slackclone.domain.mappers.DomainModel

enum class SlackChannelType {
  ONE_TO_ONE,
  GROUP
}

data class DomSlackChannel(
  val channel: SlackChannelType? = null,
  val uuid: String? = null,
  val name: String? = null,
  val createdDate: Long? = null,
  val modifiedDate: Long? = null,
  val isMuted: Boolean? = null,
  val isPrivate: Boolean? = null,
  val isStarred: Boolean? = false,
  val isShareOutSide: Boolean? = false
) : DomainModel()
