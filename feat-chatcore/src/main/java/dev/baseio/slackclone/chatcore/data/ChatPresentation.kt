package dev.baseio.slackclone.chatcore.data

import dev.baseio.slackclone.domain.mappers.UIModel

object ChatPresentation {
  data class SlackChannel(
    val name: String?,
    val isPrivate: Boolean,
    val uuid: String,
    val createdDate: String,
    val modifiedDate: String,
    val isMuted: Boolean,
  ) : UIModel()
}