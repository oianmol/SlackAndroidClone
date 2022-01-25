package com.mutualmobile.feat.githubrepos.ui.model

import android.os.Parcelable
import com.mutualmobile.praxis.domain.mappers.UIModel
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.DOMOwner
import com.mutualmobile.praxis.domain.model.DOMRepo
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIRepo(
  val id: Long,
  val name: String,
  val fullName: String,
  val description: String?,
  val url: String,
  val stars: Int,
  val forks: Int,
  val language: String?,
  val watchers: Int,
  val owner: UIOwner,
  val createDate: String,
  val updateDate: String,
  val openIssues: Int
) : UIModel(), Parcelable

@Parcelize
data class UIOwner(
  val id: Int,
  val login: String,
  val avatarUrl: String
) : UIModel(), Parcelable

data class UIReposList(
  val uiRepos: List<UIRepo>
) : UIModel()

class UIRepoMapper : UiModelMapper<DOMRepo, UIRepo> {
  override fun mapToPresentation(model: DOMRepo): UIRepo {
    return with(model) {
      UIRepo(
        id, name, fullName, description, url, stars, forks, language, watchers,
        UIOwner(id = owner.id, login = owner.login, avatarUrl = owner.avatarUrl),
        createDate, updateDate, openIssues
      )
    }
  }

  override fun mapToDomain(modelItem: UIRepo): DOMRepo {
    return with(modelItem) {
      DOMRepo(
        id, name, fullName, description, url, stars, forks, language, watchers,
        DOMOwner(id = owner.id, login = owner.login, avatarUrl = owner.avatarUrl),
        createDate, updateDate, openIssues
      )
    }
  }
}