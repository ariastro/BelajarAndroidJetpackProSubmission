package com.undeadcoder.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.undeadcoder.moviecatalogue.data.DetailEntity
import com.undeadcoder.moviecatalogue.data.MovieEntity
import com.undeadcoder.moviecatalogue.data.TvShowEntity
import com.undeadcoder.moviecatalogue.data.source.remote.RemoteDataSource
import com.undeadcoder.moviecatalogue.data.source.remote.response.Movie
import com.undeadcoder.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.undeadcoder.moviecatalogue.data.source.remote.response.TVShowDetailResponse
import com.undeadcoder.moviecatalogue.data.source.remote.response.TvShow

class MovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource{

    override fun getMovies(): LiveData<List<MovieEntity>> {
        val moviesResult = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                val movieList = mutableListOf<MovieEntity>()
                if (movies != null) {
                    movies.forEach {
                        with(it) {
                            movieList.add(MovieEntity(id, title, overview, posterPath, voteAverage))
                        }
                    }
                    moviesResult.postValue(movieList)
                }
            }
        })
        return moviesResult
    }

    override fun getDetailMovie(movieId: String): LiveData<DetailEntity> {
        val movieDetailResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieLoaded(movieDetail: MovieDetailResponse?) {
                if (movieDetail != null) {
                    with(movieDetail) {
                        val listGenres = mutableListOf<String>()

                        genres.forEach {
                            listGenres.add(it.name)
                        }

                        val detailMovie = DetailEntity(backdropPath, listGenres, id, overview, posterPath, releaseDate, runtime, title, voteAverage)
                        movieDetailResult.postValue(detailMovie)
                    }
                }
            }
        }, movieId)
        return movieDetailResult
    }

    override fun getTVShows(): LiveData<List<TvShowEntity>> {
        val tvShowsResult = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsLoaded(tvShows: List<TvShow>?) {
                val tvList = mutableListOf<TvShowEntity>()
                if (tvShows != null) {
                    tvShows.forEach {
                        with(it) {
                            tvList.add(TvShowEntity(id, name, overview, posterPath, voteAverage))
                        }
                    }
                    tvShowsResult.postValue(tvList)
                }
            }
        })
        return tvShowsResult
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity> {
        val movieDetailResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowLoaded(tvShowDetail: TVShowDetailResponse?) {
                if (tvShowDetail != null) {
                    with(tvShowDetail) {
                        val listGenres = mutableListOf<String>()

                        genres.forEach {
                            listGenres.add(it.name)
                        }

                        val detailMovie = DetailEntity(backdropPath, listGenres, id, overview, posterPath, firstAirDate, episodeRunTime.average().toInt(), name, voteAverage)
                        movieDetailResult.postValue(detailMovie)
                    }
                }
            }
        }, tvShowId)
        return movieDetailResult
    }
}