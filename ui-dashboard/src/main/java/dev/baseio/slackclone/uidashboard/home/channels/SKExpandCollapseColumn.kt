package dev.baseio.slackclone.uidashboard.home.channels

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.uidashboard.R
import dev.baseio.slackclone.uidashboard.home.ListItem
import dev.baseio.slackclone.uidashboard.home.channels.data.ExpandCollapseModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SKExpandCollapseColumn(
  expandCollapseModel: ExpandCollapseModel,
  onExpandCollapse: (isChecked: Boolean) -> Unit
) {
  Column(
    Modifier
      .fillMaxWidth()
      .padding(start = 8.dp, top = 8.dp, end = 8.dp)
  ) {
    Row(
      Modifier.fillMaxWidth().clickable {
        onExpandCollapse(!expandCollapseModel.isOpen)
      },
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(text = expandCollapseModel.title, modifier = Modifier.weight(1f))
      if (expandCollapseModel.needsPlusButton) {
        AddButton()
      }
      ToggleButton(expandCollapseModel, onExpandCollapse)
    }
    AnimatedVisibility(visible = expandCollapseModel.isOpen) {
      LazyColumn {
        items(10) {
          ListItem(icon = Icons.Default.Lock, title = stringResource(R.string.some_project))
        }
      }
    }
    Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)

  }
}

@Composable
private fun AddButton() {
  IconButton(onClick = {

  }) {
    Icon(
      imageVector = Icons.Default.Add,
      contentDescription = null,
      tint = SlackCloneColorProvider.colors.lineColor
    )
  }
}

@Composable
private fun ToggleButton(
  expandCollapseModel: ExpandCollapseModel,
  onExpandCollapse: (isChecked: Boolean) -> Unit
) {
  IconToggleButton(checked = expandCollapseModel.isOpen, onCheckedChange = {
    onExpandCollapse(it)
  }) {
    Icon(
      imageVector = if (expandCollapseModel.isOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
      contentDescription = null,
      tint = SlackCloneColorProvider.colors.lineColor
    )
  }
}