package dev.baseio.slackclone.uipreference.preferencegroups

import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import dev.baseio.slackclone.uipreference.R
import dev.baseio.slackclone.uipreference.datastoremanager.DataStoreManager
import dev.baseio.slackclone.uipreference.composables.Preference
import dev.baseio.slackclone.uipreference.settings.LanguagePreference
import dev.baseio.slackclone.uipreference.settings.TimeZonePreference
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun createTimeAndPlacePreference(
    dataStoreManager: DataStoreManager
) : Preference.PreferenceGroup{

    val timePlacePrefsLanguage = stringArrayResource(R.array.time_and_place_pref_language)
    val timePlacePrefsTimezone= stringArrayResource(R.array.time_and_place_pref_timezone)

    val locales = Locale.getAvailableLocales()
    val languageList = ArrayList<String>()
    for (i in locales.indices){
        languageList.add(locales[i].displayLanguage + " ("+locales[i].displayCountry+")")
    }

    val languagePreference by dataStoreManager.getPreferenceFlow(LanguagePreference)
        .collectAsState(initial = Locale.getDefault().displayLanguage + " ("+Locale.getDefault().displayCountry+")")
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true){
        coroutineScope.launch {
            dataStoreManager.editPreference(LanguagePreference.key,languagePreference)
        }
    }


    return Preference.PreferenceGroup(
        title = stringResource(id = R.string.time_and_place_pref),
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.ListPreference(
                request = LanguagePreference,
                title = timePlacePrefsLanguage[0],
                summary = languagePreference,
                singleLineTitle = true,
                icon = { Icon(painter = painterResource(id = R.drawable.ic_language), contentDescription = "language") },
                entries = languageList.mapIndexed { index, locale -> index.toString() to locale }.toMap()
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = TimeZonePreference,
                title = timePlacePrefsTimezone[0],
                summary = "(UTC+5:30) Chennai, Kolkata, Mumbai, New Delhi",
                singleLineTitle = true,
                icon = { Icon(painter = painterResource(id = R.drawable.ic_timezone), contentDescription = "language") },
            true
            ),
        )
    )

}