package com.undeadcoder.moviecatalogue1.ui.detail

import androidx.lifecycle.ViewModel
import com.undeadcoder.moviecatalogue1.data.DataEntity
import com.undeadcoder.moviecatalogue1.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setMovieId(movieId: String) {
        this.movieId = movieId
    }

    fun setTvShowId(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): DataEntity {
        lateinit var movie: DataEntity
        val movies = DataDummy.generateDummyMovies()
        movies.forEach {
            if (it.id == movieId) {
                movie = it
            }
        }
        return movie
    }

    fun getTVShow(): DataEntity {
        lateinit var tvShow: DataEntity
        val tvShows = DataDummy.generateDummyTvShows()
        tvShows.forEach {
            if (it.id == tvShowId) {
                tvShow = it
            }
        }
        return tvShow
    }

}