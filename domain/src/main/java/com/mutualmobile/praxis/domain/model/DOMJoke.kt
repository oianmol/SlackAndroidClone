package com.mutualmobile.praxis.domain.model

import android.os.Parcelable
import com.mutualmobile.praxis.domain.mappers.DomainModel
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