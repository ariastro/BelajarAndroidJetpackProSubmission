package com.undeadcoder.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.undeadcoder.moviecatalogue.data.DetailEntity
import com.undeadcoder.moviecatalogue.data.MovieEntity
import com.undeadcoder.moviecatalogue.data.TvShowEntity

interface MovieDataSource {

    fun getMovies(): LiveData<List<MovieEntity>>

    fun getDetailMovie(movieId: String): LiveData<DetailEntity>

    fun getTVShows(): LiveData<List<TvShowEntity>>

    fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity>

}