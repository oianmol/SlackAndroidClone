package com.mutualmobile.feat.githubrepos.ui.github.repodetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.feat.githubrepos.ui.model.UIRepo
import com.mutualmobile.feat.githubrepos.utils.getFormattedCreateDate
import com.mutualmobile.feat.githubrepos.utils.getFormattedDuration
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme

const val DATE_TIME_DETAILS = "MMM d yyyy hh:mm a"

@Composable
fun RepoDetailsScreen(uiRepo: UIRepo) {
  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      CommonTopAppBar(uiRepo.fullName)
    }) {
    PraxisSurface(
      color = PraxisTheme.colors.uiBackground,
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
    ) {
      Column(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
      ) {
        Text(
          text = "Language: ${if (uiRepo.language.isNullOrBlank()) "Empty" else uiRepo.language}",
          style = MaterialTheme.typography.body1,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          modifier = Modifier
            .padding(8.dp)
        )
        Text(
          text = "Created: ${getFormattedCreateDate(uiRepo.createDate)}",
          style = MaterialTheme.typography.body1,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          modifier = Modifier
            .padding(8.dp)
        )
        Text(
          text = "Duration: ~${getFormattedDuration(uiRepo.createDate)}",
          style = MaterialTheme.typography.body2,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          modifier = Modifier
            .padding(8.dp)
        )
      }
    }
  }
}