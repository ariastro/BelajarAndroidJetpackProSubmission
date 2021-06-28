package com.undeadcoder.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.undeadcoder.moviecatalogue.data.source.MovieRepository
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.vo.Resource

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {

    lateinit var detailMovie: LiveData<Resource<MovieEntity>>
    lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>

    fun getDetailContent(isMovie: Boolean, id: String){
        if (isMovie) {
            detailMovie = repository.getDetailMovie(id.toInt())
        } else {
            detailTvShow = repository.getDetailTvShow(id.toInt())
        }
    }

    fun setFavoriteMovie() {
        val movie = detailMovie.value
        if (movie?.data != null) {
            repository.setFavoriteMovie(movie.data)
        }
    }

    fun setFavoriteTvShow() {
        val tvShow = detailTvShow.value
        if (tvShow?.data != null) {
            repository.setFavoriteTvShow(tvShow.data)
        }
    }

}