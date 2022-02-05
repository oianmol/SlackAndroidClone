package dev.baseio.slackclone.uidashboard.compose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.model.users.DomainLayerUsers
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
      NavigationKeys.channelCreated,
    ).onStart {
      val message = savedStateHandle.get<String>(NavigationKeys.channelCreated)
      message?.let {
        emit(it)
      }
    }.map {
      useCaseGetChannel.perform(it)
    }.onEach {
        it?.let { it1 ->
          selectedChatChannel.value = channelMapper.mapToPresentation(it1)
          isChatViewClosed.value = false
        }
      }
      .launchIn(viewModelScope)

    selectedChatChannel.onEach {
      savedStateHandle.set(NavigationKeys.channelCreated, it?.uuid)
    }.launchIn(viewModelScope)
  }

  private fun preloadUsers() {
    viewModelScope.launch {
      val users = useCaseFetchUsers.perform(10)
      useCaseSaveChannel.perform(users)
    }
  }

}