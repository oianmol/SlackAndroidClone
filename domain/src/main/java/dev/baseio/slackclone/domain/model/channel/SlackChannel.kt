package dev.baseio.slackclone.domain.model.channel


enum class SlackChannelType {
  ONE_TO_ONE,
  GROUP
}

abstract class SlackChannel(val channelType: SlackChannelType) {
  abstract val uuid: String
  abstract val name: String
  abstract val createdDate: String
  abstract val modifiedDate: String
  abstract val isMuted: Boolean
}

data class OneToOneChannel(
  val otherUserName: String,
  override val uuid: String,
  override val name: String,
  override val createdDate: String,
  override val modifiedDate: String,
  override val isMuted: Boolean,
) :
  SlackChannel(SlackChannelType.ONE_TO_ONE)

data class GroupChannel(
  val description: String?,
  val createdBy: String,
  val isStarred: Boolean,
  val isPrivate: Boolean,
  override val uuid: String,
  override val name: String,
  override val createdDate: String,
  override val modifiedDate: String,
  override val isMuted: Boolean,
) : SlackChannel(SlackChannelType.GROUP)
