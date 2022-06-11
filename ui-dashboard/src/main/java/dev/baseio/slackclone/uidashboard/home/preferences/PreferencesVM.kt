package dev.baseio.slackclone.uidashboard.home.preferences

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.data.local.model.SlackPreferences
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.ItemClickStates
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.PopUpItemStates
import dev.baseio.slackclone.uidashboard.home.preferences.uicomponents.SwitchesStates
import javax.inject.Inject

@HiltViewModel
class PreferencesVM @Inject constructor() : ViewModel() {
  val preferencesList = mutableListOf<SlackPreferences>()

  val switchesStates: SwitchesStates = SwitchesStates()
  val itemClickStates: ItemClickStates = ItemClickStates()
  val popUpItemStates: PopUpItemStates = PopUpItemStates()

  init {
    createPreferencesList()
  }

  private fun createPreferencesList() {
    var index = 0
    preferencesList.addAll(
        arrayOf(
            SlackPreferences(
                index++, "Language", "English(US)", description = "",
            ),
            SlackPreferences(
                index++, "Set time zone automatically",
                "(UTC+5:30) Chennai, Kolkata, Mumbai, New Delhi", description = "",

                ),
            SlackPreferences(
                index++, "Default emoji skin tone", "hand with light skin tone", description = "",

                ),
            SlackPreferences(
                index++, "Dark mode", "System default", description = "",

                ),
            SlackPreferences(
                index++, "Allow animated images & emoji", "This only affects what you see",
                description = "",

                ),
            SlackPreferences(
                index++, "Underline links",
                "Websites and hyperlinks will be underlined in conversations", description = "",

                ),
            SlackPreferences(
                index++, "Display typing indicators", "Allow links to open in Slack",
                description = "",

                ),
            SlackPreferences(
                index++, "Open web pages in app", "Images are optimized for upload performance",
                description = "",

                ),
            SlackPreferences(
                index++, "Optimize image uploads", "Images are optimized for upload performance",
                description = "",

                ),
            SlackPreferences(
                index++, "Optimize video uploads", "Videos are optimized for upload performance",
                description = "",

                ),
            SlackPreferences(
                index++, "Show image previews", "Show previews of images and files",
                description = "",

                ),
            SlackPreferences(
                index++, "Reset cache", "Clear cached images, files and data", description = "",

                ),
            SlackPreferences(
                index++, "Force stop", "Unsaved data may be lost", description = "",

                ),
            SlackPreferences(
                index++, "Send feedback", "Let us know how we can improve", description = "",

                ),
            SlackPreferences(
                index++, "Slack calls debugging", "Let Slack see logs when needed",
                description = "",

                ),
            SlackPreferences(
                index++, "Help center", "Support articled and tutorials", description = "",

                ),
            SlackPreferences(
                index++, "Privacy policy", "How Slack collects and uses information",
                description = "",

                ),
            SlackPreferences(
                index++, "Open source licenses", "Libraries we use", description = "",

                ),
            SlackPreferences(
                index, "Version", "22.05", description = "",
            )
        )
    )
  }
}