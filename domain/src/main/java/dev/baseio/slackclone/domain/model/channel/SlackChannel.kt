package dev.baseio.slackclone.domain.model.channel


interface DomainLayerChannels {
  data class SlackChannel(
    val uuid: String? = null,
    val name: String? = null,
    val createdDate: Long? = null,
    val modifiedDate: Long? = null,
    val isMuted: Boolean? = null,
    val isPrivate: Boolean? = null,
    val isStarred: Boolean? = false,
    val isShareOutSide: Boolean? = false,
    val isOneToOne: Boolean?,
    val avatarUrl: String?
  )
}
