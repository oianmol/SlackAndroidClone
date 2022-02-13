package dev.baseio.slackclone.uipreference.model

import androidx.compose.runtime.Composable
import dev.baseio.slackclone.uipreference.datastoremanager.PreferenceRequest

/**
 * The basic building block that represents an individual setting displayed to a user in the preference hierarchy.
 */
sealed class Preference {
    abstract val title: String
    abstract val enabled: Boolean

    /**
     * A single [Preference] item
     */
    sealed class PreferenceItem<T> : Preference() {
        abstract val summary: String
        abstract val singleLineTitle: Boolean
        abstract val icon: @Composable () -> Unit

        /**
         * 	A basic [PreferenceItem] that only displays text.
         */
        data class TextPreference(
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean,
            override val icon: @Composable () -> Unit,
            override val enabled: Boolean = true,

            val onClick: () -> Unit = {}
        ) : PreferenceItem<String>()

        /**
         * 	A [PreferenceItem] that provides a two-state toggleable option.
         */
        data class SwitchPreference(
            val request: PreferenceRequest<Boolean>,
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean,
            override val icon: @Composable () -> Unit,
            override val enabled: Boolean = true,
        ) : PreferenceItem<Boolean>()

        /**
         * 	A [PreferenceItem] that displays a list of entries as a dialog.
         * 	Only one entry can be selected at any given time.
         */
        data class ListPreference(
            val request: PreferenceRequest<String>,
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean,
            override val icon: @Composable () -> Unit,
            override val enabled: Boolean = true,

            val entries: Map<String, String>,
        ) : PreferenceItem<String>()

        data class DialogPreference(
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean = true,
            override val icon: @Composable () -> Unit,
            override val enabled: Boolean = true,
            val dialogTitle: String? = null,
            val dialogSummary: String? = null,
            val content: @Composable () -> Unit,
            val negativeButtonText : String,
            val positiveButtonText : String,
            val negativeButtonClick : () -> Unit,
            val positiveButtonClick : () -> Unit,

        ) : PreferenceItem<String>()

    }

    /**
     * A container for multiple [PreferenceItem]s
     */
    data class PreferenceGroup(
        override val title: String,
        override val enabled: Boolean = true,

        val preferenceItems: List<PreferenceItem<out Any>>
    ) : Preference()
}