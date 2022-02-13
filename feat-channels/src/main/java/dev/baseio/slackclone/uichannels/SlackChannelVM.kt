package dev.baseio.slackclone.uichannels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.usecases.channels.UseCaseFetchChannels
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SlackChannelVM @Inject constructor(
  private val ucFetchChannels: UseCaseFetchChannels,
  private val chatPresentationMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) : ViewModel() {

  val channels = MutableStateFlow<Flow<List<UiLayerChannels.SlackChannel>>>(emptyFlow())

  fun allChannels() {
    channels.value = ucFetchChannels.performStreaming(null).map { channels ->
      domSlackToPresentation(channels)
    }
  }

  fun loadDirectMessageChannels() {
    channels.value = ucFetchChannels.performStreaming(null).map { channels ->
      domSlackToPresentation(channels,)
    }
  }

  fun loadStarredChannels() {
    channels.value = ucFetchChannels.performStreaming(null).map { channels ->
      domSlackToPresentation(channels)
    }
  }

  private fun domSlackToPresentation(channels: List<DomainLayerChannels.SlackChannel>) =
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }

}