package dev.baseio.slackclone.uipreference.uipreference

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.provider.Settings.System.getConfiguration
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.uipreference.R
import dev.baseio.slackclone.uipreference.datastoremanager.DataStoreManager
import dev.baseio.slackclone.uipreference.model.Preference
import dev.baseio.slackclone.uipreference.model.PreferenceIcon
import dev.baseio.slackclone.uipreference.settings.LanguagePreference
import dev.baseio.slackclone.uipreference.settings.TimeZonePreference
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CreateTimeAndPlacePreference(
    dataStoreManager: DataStoreManager
) : Preference.PreferenceGroup{



    val timePlacePrefsLanguage = stringArrayResource(R.array.time_and_place_pref_language)
    val timePlacePrefsTimezone= stringArrayResource(R.array.time_and_place_pref_timezone)

    val locales = LocalContext.current.resources.configuration.locales
    var languageList = ArrayList<String>()
    for (i in 0 until locales.size()){
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