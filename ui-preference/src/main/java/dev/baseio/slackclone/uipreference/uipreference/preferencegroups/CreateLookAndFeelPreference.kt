package dev.baseio.slackclone.uipreference.uipreference

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.baseio.slackclone.uipreference.datastoremanager.DataStoreManager
import dev.baseio.slackclone.uipreference.model.Preference
import dev.baseio.slackclone.uipreference.model.PreferenceIcon
import dev.baseio.slackclone.uipreference.settings.DarkModePreference


@Composable
fun CreateLookAndFeelPreference(
    dataStoreManager: DataStoreManager
) : Preference.PreferenceGroup{

    val darkModePreference by dataStoreManager.getPreferenceFlow(DarkModePreference)
        .collectAsState(initial = "")
    return Preference.PreferenceGroup(
        title = "Dark Mode",
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.ListPreference(
                request = DarkModePreference,
                title = "Dark Mode",
                summary = darkModePreference,
                singleLineTitle = true,
                icon = { PreferenceIcon(icon = Icons.Outlined.CheckCircle) },
                entries = mapOf(
                    "key1" to "System default",
                    "key2" to "Off",
                    "key2" to "On"
                )
            ),
        )
    )

}