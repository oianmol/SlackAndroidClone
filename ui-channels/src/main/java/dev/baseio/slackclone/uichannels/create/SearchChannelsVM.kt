package dev.baseio.slackclone.uichannels.create

import androidx.lifecycle.ViewModel
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.chatcore.injection.ChatUiModelMapper
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.usecases.channels.UseCaseFetchChannels
import dev.baseio.slackclone.domain.usecases.channels.UseCaseSearchChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchChannelsVM @Inject constructor(
  private val ucFetchChannels: UseCaseSearchChannel,
  @ChatUiModelMapper private val chatPresentationMapper: UiModelMapper<DomSlackChannel, ChatPresentation.SlackChannel>
) : ViewModel() {

  val search = MutableStateFlow("")

  var channels = MutableStateFlow(flow(""))

  private fun flow(search:String) = ucFetchChannels.performStreaming(search).map { channels ->
      channels.map { channel ->
        chatPresentationMapper.mapToPresentation(channel)
      }
    }

  fun search(newValue: String) {
    search.value = newValue
    channels.value = flow(newValue)
  }

}