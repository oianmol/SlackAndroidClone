package dev.baseio.slackclone.uichat.channels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SlackChannelVM @Inject constructor(ucFetchChannels:UseCaseFetchChannels) : ViewModel() {
}