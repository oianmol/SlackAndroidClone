package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType

interface LocalChannelsRepository {

  interface Callback<T> {
    var channelType: SlackChannelType
    fun onNextValue(value: T)
    fun onApiError(cause: Throwable)
    fun onCompleted(): Boolean
  }

  fun registerChannelUpdates(callback: Callback<List<SlackChannel>>)
  fun unregisterChange(callback: Callback<List<SlackChannel>>)
}

