package dev.baseio.slackclone.uipreference.views.widget

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.uipreference.composables.Preference
import dev.baseio.slackclone.uipreference.views.widget.text.TextPreferenceWidget

@ExperimentalMaterialApi
@Composable
internal fun SwitchPreferenceWidget(
    preference: Preference.PreferenceItem.SwitchPreference,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    TextPreferenceWidget(
        preference = preference,
        onClick = { onValueChange(!value) }
    ) {
        Switch(
            checked = value,
            onCheckedChange = { onValueChange(!value) },
            enabled = preference.enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = SlackCloneColorProvider.colors.buttonColor,
                uncheckedThumbColor = Color.LightGray,
                checkedTrackColor = SlackCloneColorProvider.colors.buttonColor,
                uncheckedTrackColor = Color.LightGray,
                checkedTrackAlpha = 0.2f
            )
        )
    }
}