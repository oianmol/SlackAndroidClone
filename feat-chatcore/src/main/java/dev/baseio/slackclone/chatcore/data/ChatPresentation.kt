package dev.baseio.slackclone.chatcore.data

import dev.baseio.slackclone.domain.mappers.UIModel

object ChatPresentation {
  data class SlackChannel(
    val name: String?,
    val isPrivate: Boolean?,
    val uuid: String?,
    val createdDate: Long?,
    val modifiedDate: Long?,
    val isMuted: Boolean?,
  ) : UIModel()
}