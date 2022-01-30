package dev.baseio.slackclone.uichat.channels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.SlackChannel
import dev.baseio.slackclone.domain.model.channel.SlackChannelType
import dev.baseio.slackclone.uichat.injection.ChatUiModelMapper
import dev.baseio.slackclone.uichat.models.ChatPresentation
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SlackChannelVM @Inject constructor(
  ucFetchChannels: UseCaseFetchChannels,
  @ChatUiModelMapper private val chatPresentationMapper: UiModelMapper<SlackChannel, ChatPresentation.SlackChannel>
) : ViewModel() {
  val channels = ucFetchChannels.performStreaming(SlackChannelType.GROUP).map { channels ->
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }
  }


}