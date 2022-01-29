package dev.baseio.slackclone.uichat.channels.data

data class ExpandCollapseModel(val id: Int, val title: String, val needsPlusButton: Boolean,
                               var isOpen:Boolean)
