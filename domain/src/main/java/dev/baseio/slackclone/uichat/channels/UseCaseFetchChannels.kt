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
    return callbackFlow {
      val callback = localChannelCallback(this, params)
      localChannelsRepository.registerChannelUpdates(callback)
      awaitClose { localChannelsRepository.unregisterChange(callback) }
    }
  }

  private fun localChannelCallback(
    producerScope: ProducerScope<List<SlackChannel>>,
    channelType: SlackChannelType
  ) =
    object :
      LocalChannelsRepository.Callback<List<SlackChannel>> {
      override fun onNextValue(value: List<SlackChannel>) {
        producerScope.trySendBlocking(value)
          .onFailure { throwable ->
            Timber.e(throwable)
          }
      }

      override var channelType: SlackChannelType = channelType

      override fun onApiError(cause: Throwable) {
        producerScope.cancel(CancellationException("API Error", cause))
      }

      override fun onCompleted() = producerScope.channel.close()
    }
}
