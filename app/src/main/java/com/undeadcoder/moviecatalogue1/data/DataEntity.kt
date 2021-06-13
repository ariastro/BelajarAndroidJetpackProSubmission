package com.undeadcoder.moviecatalogue1.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataEntity(
    var id: String,
    var title: String,
    var overview: String,
    var genre: String,
    var score: String,
    var releaseDate: String,
    var poster: String,
    var banner: String
): Parcelable