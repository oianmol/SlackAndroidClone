package dev.baseio.slackclone.data.repository

import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.repository.LocalChannelsRepository

class SlackLocalChannelsRepositoryImpl() : LocalChannelsRepository {

  override fun registerChannelUpdates(callback: LocalChannelsRepository.Callback<List<SlackChannel>>) {

  }

  override fun unregisterChange(callback: LocalChannelsRepository.Callback<List<SlackChannel>>) {

  }

}