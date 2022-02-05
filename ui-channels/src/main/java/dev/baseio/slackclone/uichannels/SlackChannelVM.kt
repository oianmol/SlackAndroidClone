package dev.baseio.slackclone.uichannels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.chatcore.injection.ChatUiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import dev.baseio.slackclone.domain.usecases.channels.UseCaseFetchChannels
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SlackChannelVM @Inject constructor(
  private val ucFetchChannels: UseCaseFetchChannels,
  @ChatUiModelMapper private val chatPresentationMapper: UiModelMapper<DomainLayer.Channels.SlackChannel, ChatPresentation.SlackChannel>
) : ViewModel() {

  val channels = MutableStateFlow<Flow<List<ChatPresentation.SlackChannel>>>(emptyFlow())

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

  private fun domSlackToPresentation(channels: List<DomainLayer.Channels.SlackChannel>) =
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }

}