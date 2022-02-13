package dev.baseio.slackclone.uichat.newchat

import androidx.lifecycle.ViewModel
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.usecases.channels.UseCaseSearchChannel
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.NavigationKeys
import dev.baseio.slackclone.navigator.SlackScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NewChatThreadVM @Inject constructor(
  private val composeNavigator: ComposeNavigator,
  private val ucFetchChannels: UseCaseSearchChannel,
  private val chatPresentationMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) :
  ViewModel() {

  val search = MutableStateFlow("")
  var users = MutableStateFlow(flow(""))

  private fun flow(search: String) = ucFetchChannels.performStreaming(search).map { channels ->
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }
  }

  fun search(newValue: String) {
    search.value = newValue
    users.value = flow(newValue)
  }

  fun navigate(channel: UiLayerChannels.SlackChannel) {
    composeNavigator.navigateBackWithResult(
      NavigationKeys.navigateChannel,
      channel.uuid!!,
      SlackScreen.Dashboard.name
    )
  }


}