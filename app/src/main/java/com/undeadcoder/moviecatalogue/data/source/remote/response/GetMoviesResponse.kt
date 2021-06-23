package com.undeadcoder.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class GetMoviesResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val movies: List<Movie>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
) : Parcelable