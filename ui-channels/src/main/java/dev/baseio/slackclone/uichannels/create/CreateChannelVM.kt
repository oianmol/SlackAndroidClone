package dev.baseio.slackclone.uichannels.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.domain.model.channel.DomainLayer
import dev.baseio.slackclone.domain.usecases.channels.UseCaseCreateChannel
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.NavigationKeys
import dev.baseio.slackclone.navigator.SlackRoute
import dev.baseio.slackclone.navigator.SlackScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateChannelVM @Inject constructor(
  private val composeNavigator: ComposeNavigator,
  private val useCaseCreateChannel: UseCaseCreateChannel
) :
  ViewModel() {

  var channel = MutableStateFlow(DomainLayer.Channels.SlackChannel())

  fun createChannel() {
    viewModelScope.launch {
      if (channel.value.name?.isNotEmpty() == true) {
        val channel = useCaseCreateChannel.perform(channel.value)
        composeNavigator.navigateBackWithResult(
          NavigationKeys.channelCreated,
          channel?.uuid!!,
          SlackScreen.Dashboard.name
        )
      }

    }
  }
}