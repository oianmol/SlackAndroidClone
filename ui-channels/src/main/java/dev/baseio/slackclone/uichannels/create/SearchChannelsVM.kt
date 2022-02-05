package dev.baseio.slackclone.uichannels.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.UiLayer
import dev.baseio.slackclone.chatcore.injection.ChatUiModelMapper
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import dev.baseio.slackclone.domain.usecases.channels.UseCaseFetchChannelCount
import dev.baseio.slackclone.domain.usecases.channels.UseCaseSearchChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchChannelsVM @Inject constructor(
  private val ucFetchChannels: UseCaseSearchChannel,
  private val useCaseFetchChannelCount: UseCaseFetchChannelCount,
  @ChatUiModelMapper private val chatPresentationMapper: UiModelMapper<DomainLayer.Channels.SlackChannel, UiLayer.Channels.SlackChannel>
) : ViewModel() {

  val search = MutableStateFlow("")
  val channelCount = MutableStateFlow(0)

  var channels = MutableStateFlow(flow(""))

  init {
    viewModelScope.launch {
      val count = useCaseFetchChannelCount.perform()
      channelCount.value = count
    }
  }

  private fun flow(search: String) = ucFetchChannels.performStreaming(search).map { channels ->
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }
  }

  fun search(newValue: String) {
    search.value = newValue
    channels.value = flow(newValue)
  }

}