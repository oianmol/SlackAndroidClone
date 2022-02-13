package dev.baseio.slackclone.uipreference.datastoremanager

import androidx.datastore.preferences.core.Preferences

open class PreferenceRequest<T>(
    val key: Preferences.Key<T>,
    val defaultValue: T,
)