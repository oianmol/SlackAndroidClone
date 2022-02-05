package dev.baseio.slackclone.chatcore.data

interface UiLayer {
  interface Channels {
    data class SlackChannel(
      val name: String?,
      val isPrivate: Boolean?,
      val uuid: String?,
      val createdDate: Long?,
      val modifiedDate: Long?,
      val isMuted: Boolean?,
    )
  }
}
