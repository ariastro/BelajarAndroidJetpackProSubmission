package com.undeadcoder.moviecatalogue.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class DetailEntity(
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genres")
    val genres: List<String>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "runtime")
    val runtime: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "vote_average")
    val voteAverage: Double
) : Parcelable