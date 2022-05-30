package dev.baseio.slackclone.uidashboard.home.preferences.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.DarkBlue
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography

@Composable
fun RadioButtonPickerDialog(
  options: List<String>,
  selectedValue: MutableState<String> = mutableStateOf("English"),
  onDismiss: () -> Unit = {},
  onConfirm: () -> Unit = {},
) {
  SlackCloneTheme {
    AlertDialog(
        backgroundColor = SlackCloneColorProvider.colors.uiBackground,
        onDismissRequest = {
          onDismiss()
        }, confirmButton = {
      TextButton(onClick = {
        onConfirm()
      }) {
        Text(
            text = "OK", style = SlackCloneTypography.body1.copy(
            color = DarkBlue
        )
        )
      }
    }, dismissButton = {
      TextButton(onClick = {
        onDismiss()
      }) {
        Text(
            text = "CANCEL", style = SlackCloneTypography.body1.copy(
            color = DarkBlue
        )
        )
      }
    }, title = {
      Text(
          text = "Language", style = SlackCloneTypography.body1
      )
    }, text = {
      LazyColumn(
          modifier = Modifier
              .fillMaxWidth()
              .background(
                  color = SlackCloneColorProvider.colors.uiBackground
              )
      ) {
        itemsIndexed(options) { index, _ ->
          RadioButtonsUI(options[index], selectedValue) { updatedValue ->
            selectedValue.value = updatedValue
          }
        }
      }
    }
    )
  }
}

@Composable
fun RadioButtonsUI(
  option: String,
  selectedItem: MutableState<String>,
  onOptionClick: (String) -> Unit
) {
  val isItemSelected: (String) -> Boolean = {
    selectedItem.value == it
  }
  Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
          .padding(horizontal = 8.dp)
          .selectable(
              role = Role.RadioButton,
              onClick = { onOptionClick(option) },
              selected = isItemSelected(option)
          )
  ) {
    RadioButton(
        selected = isItemSelected(option), onClick = null, colors = RadioButtonDefaults.colors(
        selectedColor = DarkBlue,
        unselectedColor = SlackCloneColorProvider.colors.buttonColor
    )
    )
    Text(
        text = option, style = SlackCloneTypography.body1.copy(
        color = SlackCloneColorProvider.colors.textPrimary
    )
    )
  }
}
