package com.undeadcoder.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getAllMovies()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = movieDao.getAllTvShows()

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = movieDao.getFavoriteTvShows()

    fun getDetailMovie(id: Int): LiveData<MovieEntity> = movieDao.getDetailMovie(id)

    fun getDetailTvShow(id: Int): LiveData<TvShowEntity> = movieDao.getDetailTvShow(id)

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun updateMovie(movie: MovieEntity) {
        movieDao.updateMovie(movie)
    }

    fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)

    fun updateTvShow(tvShow: TvShowEntity) {
        movieDao.updateTvShow(tvShow)
    }

    fun setFavoriteMovie(movie: MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        movieDao.updateTvShow(tvShow)
    }
}