package dev.baseio.slackclone.uichannels.directmessages

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.model.message.DomainLayerMessages
import dev.baseio.slackclone.domain.usecases.channels.UseCaseFetchChannelsWithLastMessage
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DMessageViewModel @Inject constructor(
  private val useCaseFetchChannels: UseCaseFetchChannelsWithLastMessage,
  private val channelPresentationMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>,
) : ViewModel() {


  val channels = MutableStateFlow(fetchFlow())

  fun refresh() {
    channels.value = useCaseFetchChannels.performStreaming(null)
  }

  fun fetchFlow(): Flow<PagingData<DomainLayerMessages.LastMessage>> {
    return useCaseFetchChannels.performStreaming(null)
  }

  fun mapToUI(channel: DomainLayerChannels.SlackChannel): UiLayerChannels.SlackChannel {
    return channelPresentationMapper.mapToPresentation(channel)
  }

}