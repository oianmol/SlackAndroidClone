package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.model.channel.SlackChannel

interface LocalChannelsRepository {
  interface Callback<T> {
    fun onNextValue(value: T)
    fun onApiError(cause: Throwable)
    fun onCompleted(): Boolean
  }

  fun registerChannelUpdates(callback: Callback<List<SlackChannel>>)
  fun unregisterChange(callback: Callback<List<SlackChannel>>)
}

