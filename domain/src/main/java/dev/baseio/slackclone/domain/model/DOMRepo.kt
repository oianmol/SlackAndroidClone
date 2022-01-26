package dev.baseio.slackclone.domain.model

import android.os.Parcelable
import dev.baseio.slackclone.domain.mappers.DomainModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DOMRepo(
  val id: Long,
  val name: String,
  val fullName: String,
  val description: String?,
  val url: String,
  val stars: Int,
  val forks: Int,
  val language: String?,
  val watchers: Int,
  val owner: DOMOwner,
  val createDate: String,
  val updateDate: String,
  val openIssues: Int
) : DomainModel(), Parcelable

@Parcelize
data class DOMOwner(
  val id: Int,
  val login: String,
  val avatarUrl: String
) : DomainModel(), Parcelable

data class DOMRepoList(
  val domRepos: List<DOMRepo>
) : DomainModel()