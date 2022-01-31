package dev.baseio.slackclone.uichat

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.baseio.slackclone.data.local.dao.SlackMessageDao
import javax.inject.Inject

@HiltViewModel
class ChatThreadVM @Inject constructor(slackMessageDao: SlackMessageDao) : ViewModel() {
    val chatPager = Pager(PagingConfig(pageSize = 20)) {
        slackMessageDao.messagesByDate()
    }

}