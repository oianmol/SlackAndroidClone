package dev.baseio.slackclone.uipreference.views.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider.colors
import dev.baseio.slackclone.uipreference.model.Preference
import dev.baseio.slackclone.uipreference.views.widget.text.TextPreferenceWidget

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
internal fun ListPreferenceWidget(
    preference: Preference.PreferenceItem.ListPreference,
    value: String,
    onValueChange: (String) -> Unit
) {
    val (isDialogShown, showDialog) = remember { mutableStateOf(false) }

    TextPreferenceWidget(
        preference = preference,
        summary = preference.entries[value],
        onClick = { showDialog(!isDialogShown) },

    )

    if (isDialogShown) {
        AlertDialog(
            onDismissRequest = { showDialog(!isDialogShown) },
            title = { Text(text = preference.title,
                fontWeight = FontWeight.Bold) },
            buttons = {
                val lazyListState = LazyListState(0)
                    LazyColumn( modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
                        state =lazyListState){
                        preference.entries.forEach { current ->
                            val isSelected = value == current.key
                            val onSelected = {
                                onValueChange(current.key)
                                showDialog(!isDialogShown)
                            }
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .selectable(
                                            selected = isSelected,
                                            onClick = { if (!isSelected) onSelected() }
                                        )
                                        .padding(16.dp)
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { if (!isSelected) onSelected() },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = colors.textPrimary,
                                            unselectedColor = Color.LightGray,
                                            disabledColor = Color.LightGray
                                        )
                                    )
                                    Text(
                                        text = current.value,
                                        style = MaterialTheme.typography.body1.merge(),
                                        modifier = Modifier.padding(start = 16.dp),
                                        color = SlackCloneColorProvider.colors.textPrimary
                                    )
                                }
                            }
                        }
                    }

            },
            properties = DialogProperties(
                usePlatformDefaultWidth = true
            ),
            backgroundColor = SlackCloneColorProvider.colors.uiBackground
        )
    }
}