package dev.baseio.slackclone.uipreference.views.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.uipreference.model.Preference
import dev.baseio.slackclone.uipreference.views.widget.text.TextPreferenceWidget

@OptIn(ExperimentalMaterialApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
internal fun DialogPreferenceWidget(
    preference: Preference.PreferenceItem.DialogPreference
){
    val (isDialogShown, showDialog) = remember { mutableStateOf(false) }
    TextPreferenceWidget(
        preference = preference,
        onClick = { showDialog(!isDialogShown) },
    )

    if (isDialogShown) {
        AlertDialog(
            backgroundColor = SlackCloneColorProvider.colors.uiBackground,
            onDismissRequest = { showDialog(!isDialogShown) },
            title = { Text(preference.dialogTitle?: preference.title) },
            text = {Text(preference.dialogSummary?:preference.summary)},
            buttons = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    preference.content.invoke()

                Row(modifier = Modifier
                    .fillMaxWidth(),

                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = preference.negativeButtonText,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable {
                                showDialog(!isDialogShown)
                                preference.negativeButtonClick
                            },
                        color = SlackCloneColorProvider.colors.textPrimary,
                    )
                    Text(
                        text = preference.positiveButtonText,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable {
                                showDialog(!isDialogShown)
                                preference.negativeButtonClick
                            },
                        color = SlackCloneColorProvider.colors.textPrimary
                    )
                }
                }
            },

            properties = DialogProperties(
                usePlatformDefaultWidth = true
            ),
        )
    }
}