package com.undeadcoder.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
): Parcelable