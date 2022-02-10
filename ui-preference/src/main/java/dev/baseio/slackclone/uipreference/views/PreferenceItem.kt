package dev.baseio.slackclone.uipreference.views

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.datastore.preferences.core.Preferences
import dev.baseio.slackclone.uipreference.datastoremanager.DataStoreManager
import dev.baseio.slackclone.uipreference.model.Preference
import dev.baseio.slackclone.uipreference.views.widget.DialogPreferenceWidget
import dev.baseio.slackclone.uipreference.views.widget.ListPreferenceWidget
import dev.baseio.slackclone.uipreference.views.widget.SwitchPreferenceWidget
import dev.baseio.slackclone.uipreference.views.widget.text.TextPreferenceWidget
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
internal fun PreferenceItem(
    item: Preference.PreferenceItem<*>,
    prefs: Preferences?,
    dataStoreManager: DataStoreManager
) {
    val scope = rememberCoroutineScope()

    when (item) {
        is Preference.PreferenceItem.SwitchPreference -> {
            SwitchPreferenceWidget(
                preference = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                }
            )
        }
        is Preference.PreferenceItem.ListPreference -> {
            ListPreferenceWidget(
                preference = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                }
            )
        }
        is Preference.PreferenceItem.TextPreference -> {
            TextPreferenceWidget(
                preference = item,
                onClick = item.onClick,
            )
        }
        is Preference.PreferenceItem.DialogPreference -> {
            DialogPreferenceWidget(
                preference = item,
            )
        }
    }
}