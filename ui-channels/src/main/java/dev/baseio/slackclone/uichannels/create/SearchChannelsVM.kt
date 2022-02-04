package dev.baseio.slackclone.uichannels.create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.chatcore.injection.ChatUiModelMapper
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.usecases.channels.UseCaseFetchChannels
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchChannelsVM @Inject constructor(
  ucFetchChannels: UseCaseFetchChannels,
  @ChatUiModelMapper private val chatPresentationMapper: UiModelMapper<DomSlackChannel, ChatPresentation.SlackChannel>
) : ViewModel() {
  val channels = ucFetchChannels.performStreaming(null).map { channels ->
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }
  }
}