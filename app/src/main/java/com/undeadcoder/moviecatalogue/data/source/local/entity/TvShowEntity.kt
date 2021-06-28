package com.undeadcoder.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tv_show")
data class TvShowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "overview")
        val overview: String,
        @ColumnInfo(name = "poster_path")
        val posterPath: String?,
        @ColumnInfo(name = "backdrop_path")
        val backdropPath: String?,
        @ColumnInfo(name = "vote_average")
        val voteAverage: Double?,
        @ColumnInfo(name = "genres")
        val genres: String?,
        @ColumnInfo(name = "release_date")
        val releaseDate: String,
        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
) : Parcelable