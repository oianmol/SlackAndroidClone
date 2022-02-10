package dev.baseio.slackclone.uipreference.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.baseio.slackclone.uipreference.datastoremanager.PreferenceRequest

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object LanguagePreference : PreferenceRequest<String>(stringPreferencesKey("pref_language"), "English (US)")
object TimeZonePreference : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_timezone"), true)

object DarkModePreference : PreferenceRequest<String>(stringPreferencesKey("pref_darkMode"), "System default")

object AllowAnimatedImagesAndEmojiPreference : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_allowAnimatedImagesAndEmoji"), true)
object UnderlineLinksPreference : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_underlineLinks"), true)
object DisplayTypingIndicatorsPreference : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_displayTypingIndicators"), true)

object OpenWebpagesInAppPreference : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_openWebPages"), true)
object OptimiseImageUploadsPreference : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_optimiseImageUploads"), true)
object OptimiseVideoUploadsPreference : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_optimiseVideoUploads"), true)
object ShowImagePreviewsPreference : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_showImagePreviews"), true)
