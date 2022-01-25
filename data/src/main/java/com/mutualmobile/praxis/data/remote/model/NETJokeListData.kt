package dev.baseio.slackclone.data.remote.model

import com.google.gson.annotations.SerializedName
import dev.baseio.slackclone.data.mapper.EntityMapper
import dev.baseio.slackclone.data.mapper.DataModel
import dev.baseio.slackclone.domain.model.DOMJoke
import dev.baseio.slackclone.domain.model.DOMJokeList

data class NETJokeListData(
  @SerializedName("type")
  val type: String,
  @SerializedName("value")
  val value: List<NETJokeData>
) : DataModel()

data class NETJokeData(
  @SerializedName("id")
  val id: Int,
  @SerializedName("joke")
  val joke: String
) : DataModel()

class JokesListResponseMapper : EntityMapper<DOMJokeList, NETJokeListData> {
  override fun mapToDomain(entity: NETJokeListData): DOMJokeList {
    return DOMJokeList(entity.type, entity.value.map { DOMJoke(it.id, it.joke) })
  }

  override fun mapToEntity(model: DOMJokeList): NETJokeListData {
    return NETJokeListData(model.type, model.DOMJokes.map { NETJokeData(it.id, it.joke) })
  }

}