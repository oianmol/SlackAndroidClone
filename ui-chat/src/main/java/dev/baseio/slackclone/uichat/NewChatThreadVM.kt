package dev.baseio.slackclone.uichat

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.chatcore.data.UiLayer
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NewChatThreadVM @Inject constructor() : ViewModel() {

  val search = MutableStateFlow("")
  val users = MutableStateFlow<List<UiLayer.Channels.SlackChannel>>(listOf())

  fun search(newValue: String) {
    search.value = newValue
  }


}