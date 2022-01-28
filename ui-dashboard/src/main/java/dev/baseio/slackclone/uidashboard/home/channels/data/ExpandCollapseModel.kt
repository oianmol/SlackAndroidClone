package dev.baseio.slackclone.uidashboard.home.channels.data

data class ExpandCollapseModel(val id: Int, val title: String, val needsPlusButton: Boolean,
                               var isOpen:Boolean)
