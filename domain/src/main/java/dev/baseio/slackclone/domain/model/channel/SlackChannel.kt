package dev.baseio.slackclone.domain.model.channel

import dev.baseio.slackclone.domain.mappers.DomainModel

enum class SlackChannelType {
  ONE_TO_ONE,
  GROUP
}

abstract class SlackChannel(val channelType: SlackChannelType) : DomainModel() {
  abstract val uuid: String
  abstract val name: String?
  abstract val createdDate: String
  abstract val modifiedDate: String
  abstract val isMuted: Boolean
  abstract val isPrivate: Boolean

}

data class SlackOneToOneChannel(
  override val uuid: String,
  override val name: String?,
  override val createdDate: String,
  override val modifiedDate: String,
  override val isMuted: Boolean,
  override val isPrivate: Boolean
) :
  SlackChannel(SlackChannelType.ONE_TO_ONE)

data class SlackGroupChannel(
  val description: String?,
  val createdBy: String,
  val isStarred: Boolean,
  override val uuid: String,
  override val name: String?,
  override val createdDate: String,
  override val modifiedDate: String,
  override val isMuted: Boolean,
  override val isPrivate: Boolean
) : SlackChannel(SlackChannelType.GROUP)
