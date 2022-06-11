package dev.baseio.slackclone.data.local.model

import java.util.Date

data class SlackPreferences(
  var id: Int,
  var name: String,
  var value: String,
  var lastModified: Long = Date().time,
  var description: String,
)