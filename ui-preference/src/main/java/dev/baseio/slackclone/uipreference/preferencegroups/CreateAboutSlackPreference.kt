package dev.baseio.slackclone.uipreference.preferencegroups

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.uipreference.datastoremanager.DataStoreManager
import dev.baseio.slackclone.uipreference.composables.Preference

@Composable
fun CreateAboutSlackPreference(
    dataStoreManager: DataStoreManager
) : Preference.PreferenceGroup {
    return Preference.PreferenceGroup(
        title = "About Slack",
        enabled = true,
        preferenceItems = listOf(
    Preference.PreferenceItem.TextPreference(
        "Privacy Policy",
        "How Slack collects and uses information",
        true,
        icon = {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        },
        onClick = {}
    ),
            Preference.PreferenceItem.TextPreference(
                "Open source licenses",
                "Libraries we use",
                true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                onClick = {}
            ),
            Preference.PreferenceItem.TextPreference(
                "Version",
                "22.01.30.0-30011403-3677",
                true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                onClick = {}
            )
        ))
}