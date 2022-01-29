package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.reusable.SlackListItem
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uidashboard.R
import dev.baseio.slackclone.uidashboard.home.search.SearchCancel

@Composable
fun SearchMessagesUI() {
  SlackCloneSurface(
    color = SlackCloneColorProvider.colors.uiBackground,
    modifier = Modifier.fillMaxSize()
  ) {
    Column() {
      SearchTopAppBar()
      Content()
    }
  }
}

@Composable
private fun SearchTopAppBar() {
  SlackSurfaceAppBar(
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
    contentPadding = PaddingValues(8.dp)
  ) {
    SearchCancel()
  }

}

@Composable
private fun Content() {
  Column(Modifier.verticalScroll(rememberScrollState())) {
    SlackListItem(
      icon = Icons.Default.ShoppingCart,
      title = stringResource(R.string.browse_people)
    )
    SlackListItem(icon = Icons.Default.Search, title = stringResource(R.string.browse_channels))
    SlackListDivider()
    // Recent Searches
    SearchText(stringResource(R.string.recent_searches))
    repeat(5) {
      SlackListItem(
        icon = Icons.Default.Favorite,
        title = "in:#android_india",
        trailingItem = Icons.Default.Clear
      )
    }
    SlackListDivider()
    // Narrow Your Search
    SearchText(stringResource(R.string.narrow_your_search))
    repeat(5) {
      SlackListItemTrailingView(
        icon = Icons.Default.Favorite,
        title = "from:",
        trailingView = {
          Text(text = "Ex: @zoemaxwell")
        }
      )
    }
  }
}

@Composable
private fun SearchText(title: String) {
  Text(
    text = title,
    style = SlackCloneTypography.caption.copy(fontWeight = FontWeight.SemiBold),
    modifier = Modifier.padding(16.dp)
  )
}

@Composable
fun SlackListDivider() {
  Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
}

