package com.mutualmobile.praxis.data.remote.model

import com.google.gson.annotations.SerializedName
import com.mutualmobile.praxis.data.mapper.DataModel
import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.domain.model.DOMOwner
import com.mutualmobile.praxis.domain.model.DOMRepo
import com.mutualmobile.praxis.domain.model.DOMRepoList

data class NETRepoListData(
  @SerializedName("total_count")
  val total: Int = 0,
  @SerializedName("items")
  val repos: List<NETRepoData> = emptyList(),
  @SerializedName("nextPage")
  val nextPage: Int? = null
) : DataModel()

data class NETRepoData(
  @SerializedName("id")
  val id: Long,
  @SerializedName("name")
  val name: String,
  @SerializedName("full_name")
  val fullName: String,
  @SerializedName("description")
  val description: String?,
  @SerializedName("html_url")
  val url: String,
  @SerializedName("stargazers_count")
  val stars: Int,
  @SerializedName("forks_count")
  val forks: Int,
  @SerializedName("language")
  val language: String?,
  @SerializedName("watchers")
  val watchers: Int,
  @SerializedName("owner")
  val owner: Owner?,
  @SerializedName("created_at")
  val createDate: String,
  @SerializedName("updated_at")
  val updateDate: String,
  @SerializedName("open_issues")
  val openIssues: Int
) : DataModel()

data class Owner(
  val id: Int,
  val login: String,
  val avatar_url: String
) : DataModel()

class RepoListResponseMapper : EntityMapper<DOMRepoList, NETRepoListData> {

  override fun mapToDomain(entity: NETRepoListData): DOMRepoList {
    return DOMRepoList(
      domRepos = entity.repos.map { entityRepo ->
        with(entityRepo) {
          DOMRepo(
            id = id,
            name = fullName,
            fullName = fullName,
            description = description,
            url = url,
            stars = stars,
            forks = forks,
            language = language,
            watchers = watchers,
            owner = DOMOwner(
              id = owner?.id ?: 0,
              login = owner?.login.orEmpty(),
              avatarUrl = owner?.avatar_url.orEmpty()
            ),
            createDate = createDate,
            updateDate = updateDate,
            openIssues = openIssues
          )
        }
      })
  }

  override fun mapToEntity(model: DOMRepoList): NETRepoListData {
    return NETRepoListData(
      repos = model.domRepos.map { domRepo ->
        with(domRepo) {
          NETRepoData(
            id = id,
            name = name,
            fullName = fullName,
            description = description,
            url = url,
            stars = stars,
            forks = forks,
            language = language,
            watchers = watchers,
            owner = Owner(id = owner.id, login = owner.login, avatar_url = owner.avatarUrl),
            createDate = createDate,
            updateDate = updateDate,
            openIssues = openIssues
          )
        }
      })
  }
}