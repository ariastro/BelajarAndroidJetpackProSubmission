package com.undeadcoder.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show")
    fun getAllTvShows() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show WHERE isFavorite = 1")
    fun getFavoriteTvShows() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getDetailMovie(id: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show WHERE id = :id")
    fun getDetailTvShow(id: Int) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateMovie(movie : MovieEntity)

    @Update
    fun updateTvShow(tvShows: TvShowEntity)

}