package dev.baseio.slackclone.uidashboard.compose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.ChatPresentation
import dev.baseio.slackclone.chatcore.injection.ChatUiModelMapper
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomSlackChannel
import dev.baseio.slackclone.domain.usecases.channels.UseCaseGetChannel
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.NavigationKeys
import dev.baseio.slackclone.navigator.SlackScreen
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val composeNavigator: ComposeNavigator,
  private val useCaseGetChannel: UseCaseGetChannel,
  @ChatUiModelMapper private val uiModelMapper: UiModelMapper<DomSlackChannel, ChatPresentation.SlackChannel>
) : ViewModel() {

  val selectedChatChannel = MutableStateFlow<ChatPresentation.SlackChannel?>(null)
  val isChatViewClosed = MutableStateFlow(true)

  init {
    observeChannelCreated()
  }

  private fun observeChannelCreated() {
    composeNavigator.observeResult<String>(
      NavigationKeys.channelCreated,
    ).onStart {
      val message = savedStateHandle.get<String>(NavigationKeys.channelCreated)
      message?.let {
        emit(it)
      }
    }.map {
      useCaseGetChannel.perform(it)
    }
      .onEach {
        it?.let { it1 ->
          selectedChatChannel.value = uiModelMapper.mapToPresentation(it1)
          isChatViewClosed.value = false
        }

      }
      .launchIn(viewModelScope)

    selectedChatChannel.onEach {
      savedStateHandle.set(NavigationKeys.channelCreated, it?.uuid)
    }.launchIn(viewModelScope)
  }

}