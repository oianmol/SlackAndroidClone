package dev.baseio.slackclone.domain.model

import android.os.Parcelable
import dev.baseio.slackclone.domain.mappers.DomainModel
import kotlinx.parcelize.Parcelize

/**
 * Created by Vipul Asri on 18/01/21.
 */

@Parcelize
data class DOMJoke(
    val id: Int,
    val joke: String
) : DomainModel(), Parcelable

data class DOMJokeList(val type: String, val DOMJokes: List<DOMJoke>) : DomainModel()