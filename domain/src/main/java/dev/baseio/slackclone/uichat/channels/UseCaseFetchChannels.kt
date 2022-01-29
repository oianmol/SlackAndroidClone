package dev.baseio.slackclone.uichat.channels

import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import dev.baseio.slackclone.domain.repository.LocalChannelsRepository
import dev.baseio.slackclone.domain.usecases.BaseUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException

class UseCaseFetchChannels(private val localChannelsRepository: LocalChannelsRepository) :
  BaseUseCase<List<SlackChannel>, SlackChannelType> {
  override suspend fun performStreaming(params: SlackChannelType): Flow<List<SlackChannel>> {
    return localChannelsRepository.fetchChannels(params)
  }
}
