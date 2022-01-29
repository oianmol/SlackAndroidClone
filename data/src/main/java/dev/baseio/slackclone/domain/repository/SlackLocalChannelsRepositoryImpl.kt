package dev.baseio.slackclone.domain.repository

import dev.baseio.slackclone.domain.model.channel.SlackChannel

class SlackLocalChannelsRepositoryImpl : LocalChannelsRepository {

  override fun registerChannelUpdates(callback: LocalChannelsRepository.Callback<List<SlackChannel>>) {

  }

  override fun unregisterChange(callback: LocalChannelsRepository.Callback<List<SlackChannel>>) {

  }

}