package dev.baseio.slackclone.uipreference.uipreference

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.uipreference.datastoremanager.DataStoreManager
import dev.baseio.slackclone.uipreference.model.Preference
import dev.baseio.slackclone.uipreference.settings.SlackCallsDebuggingPreference

@Composable
fun CreateTroubleShootingPreference(
    dataStoreManager: DataStoreManager
) : Preference.PreferenceGroup {

    return Preference.PreferenceGroup(
        title = "TroubleShooting",
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.DialogPreference(
                title = "Reset Cache",
                summary = "Clear cached images, files and data",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                negativeButtonText = "No",
                positiveButtonText = "Yes",
                negativeButtonClick = {

                },
                positiveButtonClick = {

                },
                content = {

                }

            ),
            Preference.PreferenceItem.DialogPreference(
                title = "Force stop",
                summary = "Unsaved data may be lost",
                dialogTitle = "Unsaved data may be lost",
                dialogSummary = "Are you sure you want to force Slack clone to stop running?",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                negativeButtonText = "Cancel",
                positiveButtonText = "Force Stop",
                negativeButtonClick = {

                },
                positiveButtonClick = {

                },
                content = {

                }

            ),
            Preference.PreferenceItem.DialogPreference(
                title = "Send feedback",
                summary = "Let us know how we can improve",
                dialogTitle = "Compose feedback",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                negativeButtonText = "CANCEL",
                positiveButtonText = "SEND",
                negativeButtonClick = {

                },
                positiveButtonClick = {

                },
                content = {
                    val textState = remember { mutableStateOf(TextFieldValue()) }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        TextField(value = textState.value, onValueChange = {textState.value = it},
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = SlackCloneColorProvider.colors.uiBackground
                            ))
                        Text(text = "We will respond via email to feedback and questions. You may also send feedback to feedback@slack.com",
                            modifier = Modifier.padding(16.dp))
                    }


                }

            ),
            Preference.PreferenceItem.SwitchPreference(
                request = SlackCallsDebuggingPreference,
                title = "Slack Calls Debugging",
                summary = "Let Slack see logs when needed",
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
            Preference.PreferenceItem.TextPreference(
                "Help Center",
                "Support Articles and Tutorials",
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

        )
    )
}