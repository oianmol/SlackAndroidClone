package com.mutualmobile.feat.jokes.ui.model

import android.os.Parcelable
import com.mutualmobile.praxis.domain.mappers.UIModel
import com.mutualmobile.praxis.domain.model.DOMJoke
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIJoke(val jokeId: Int, val joke: String) : UIModel(), Parcelable

class UIJokeMapper : UiModelMapper<DOMJoke, UIJoke> {
  override fun mapToPresentation(model: DOMJoke): UIJoke {
    return UIJoke(model.id, model.joke)
  }

  override fun mapToDomain(modelItem: UIJoke): DOMJoke {
    return DOMJoke(modelItem.jokeId, modelItem.joke)
  }

}