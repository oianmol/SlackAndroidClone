package dev.baseio.slackclone.uipreference.preferencegroups

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.uipreference.datastoremanager.DataStoreManager
import dev.baseio.slackclone.uipreference.composables.Preference
import dev.baseio.slackclone.uipreference.settings.AllowAnimatedImagesAndEmojiPreference
import dev.baseio.slackclone.uipreference.settings.DisplayTypingIndicatorsPreference
import dev.baseio.slackclone.uipreference.settings.UnderlineLinksPreference


@Composable
fun CreateAccessibilityPreference(
    dataStoreManager: DataStoreManager
) : Preference.PreferenceGroup {

    val allowAnimatedImagesAndEmojiPreference by dataStoreManager.getPreferenceFlow(
        AllowAnimatedImagesAndEmojiPreference
    )
        .collectAsState(initial = true)
    val underlineLinksPreference by dataStoreManager.getPreferenceFlow(UnderlineLinksPreference)
        .collectAsState(initial = true)
    val displayTypingIndicatorsPreference by dataStoreManager.getPreferenceFlow(
        DisplayTypingIndicatorsPreference
    )
        .collectAsState(initial = true)
    return Preference.PreferenceGroup(
        title = "Accessibility",
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.SwitchPreference(
                request = AllowAnimatedImagesAndEmojiPreference,
                title = "Allow animated images $ emoji",
                summary = "This only affects what you see",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = UnderlineLinksPreference,
                title = "Underline links",
                summary = "Websites and hyperlinks will be underlined in conversations",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = DisplayTypingIndicatorsPreference,
                title = "Display Typing Indicators",
                summary = "See when someone is typing",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
        )
    )


}