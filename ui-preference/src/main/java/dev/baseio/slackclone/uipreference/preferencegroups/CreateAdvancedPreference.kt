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
import dev.baseio.slackclone.uipreference.settings.OpenWebpagesInAppPreference
import dev.baseio.slackclone.uipreference.settings.OptimiseImageUploadsPreference
import dev.baseio.slackclone.uipreference.settings.OptimiseVideoUploadsPreference
import dev.baseio.slackclone.uipreference.settings.ShowImagePreviewsPreference


@Composable
fun CreateAdvancedPreference(
    dataStoreManager: DataStoreManager
) : Preference.PreferenceGroup{

    val openWebpagesInAppPreference by dataStoreManager.getPreferenceFlow(
        OpenWebpagesInAppPreference
    )
        .collectAsState(initial = true)
    val optimiseImageUploadsPreference by dataStoreManager.getPreferenceFlow(
        OptimiseImageUploadsPreference
    )
        .collectAsState(initial = true)
    val optimiseVideoUploadsPreference by dataStoreManager.getPreferenceFlow(
        OptimiseVideoUploadsPreference
    )
        .collectAsState(initial = true)
    val showImagePreviewsPreference by dataStoreManager.getPreferenceFlow(
        ShowImagePreviewsPreference
    )
        .collectAsState(initial = true)
    return Preference.PreferenceGroup(
        title = "Advanced",
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.SwitchPreference(
                request = OpenWebpagesInAppPreference,
                title = "Open web pages in app",
                summary = "Allow links to open in slack",
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
                request = OptimiseImageUploadsPreference,
                title = "Optimise Image Uploads",
                summary = "Images are optimised for upload performance",
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
                request = OptimiseVideoUploadsPreference,
                title = "Optimise Video Uploads",
                summary = "Videos are optimised for upload performance",
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
                request = ShowImagePreviewsPreference,
                title = "Show image previews",
                summary = "Show previews of images and files",
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