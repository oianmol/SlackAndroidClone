package dev.baseio.slackclone.uidashboard.compose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.usecases.channels.UseCaseCreateChannels
import dev.baseio.slackclone.domain.usecases.channels.UseCaseFetchUsers
import dev.baseio.slackclone.domain.usecases.channels.UseCaseGetChannel
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.NavigationKeys
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val composeNavigator: ComposeNavigator,
  private val useCaseGetChannel: UseCaseGetChannel,
  private val useCaseFetchUsers: UseCaseFetchUsers,
  private val useCaseSaveChannel: UseCaseCreateChannels,
  private val channelMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) : ViewModel() {

  val selectedChatChannel = MutableStateFlow<UiLayerChannels.SlackChannel?>(null)
  val isChatViewClosed = MutableStateFlow(true)

  init {
    observeChannelCreated()
    preloadUsers()
  }

  private fun observeChannelCreated() {
    composeNavigator.observeResult<String>(
      NavigationKeys.navigateChannel,
    ).onStart {
      val message = savedStateHandle.get<String>(NavigationKeys.navigateChannel)
      message?.let {
        emit(it)
      }
    }.map {
      useCaseGetChannel.perform(it)
    }.onEach { slackChannel ->
      navigateChatThreadForChannel(slackChannel)
    }
      .launchIn(viewModelScope)

    selectedChatChannel.onEach {
      savedStateHandle.set(NavigationKeys.navigateChannel, it?.uuid)
    }.launchIn(viewModelScope)
  }

  private fun navigateChatThreadForChannel(slackChannel: DomainLayerChannels.SlackChannel?) {
    slackChannel?.let {
      selectedChatChannel.value = channelMapper.mapToPresentation(it)
      isChatViewClosed.value = false
    }
  }

  private fun preloadUsers() {
    viewModelScope.launch {
      val users = useCaseFetchUsers.perform(10)
      useCaseSaveChannel.perform(users)
    }
  }

}