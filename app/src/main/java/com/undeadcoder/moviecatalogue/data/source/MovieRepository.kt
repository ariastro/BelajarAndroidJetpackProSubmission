package com.undeadcoder.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.undeadcoder.moviecatalogue.data.source.local.LocalDataSource
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.data.source.remote.ApiResponse
import com.undeadcoder.moviecatalogue.data.source.remote.RemoteDataSource
import com.undeadcoder.moviecatalogue.data.source.remote.response.Movie
import com.undeadcoder.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.undeadcoder.moviecatalogue.data.source.remote.response.TVShowDetailResponse
import com.undeadcoder.moviecatalogue.data.source.remote.response.TvShow
import com.undeadcoder.moviecatalogue.utils.AppExecutors
import com.undeadcoder.moviecatalogue.vo.Resource

class MovieRepository(private val remoteDataSource: RemoteDataSource,
                      private val localDataSource: LocalDataSource,
                      private val appExecutors: AppExecutors) : MovieDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                    remoteDataSource.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                data.forEach {
                    val movie = MovieEntity(
                            id = it.id,
                            backdropPath = it.backdropPath,
                            genres = null,
                            overview = it.overview,
                            posterPath = it.posterPath,
                            releaseDate = it.releaseDate,
                            runtime = 0,
                            title = it.title,
                            voteAverage = it.voteAverage,
                            isFavorite = false,
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getDetailMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                    data != null && data.runtime == 0 && data.genres.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                    remoteDataSource.getDetailMovie(movieId.toString())

            override fun saveCallResult(data: MovieDetailResponse) {
                var genres = ""
                data.genres.forEach {
                    genres += "${it.name}, "
                }

                val movie = MovieEntity(
                        id = data.id,
                        backdropPath = data.backdropPath,
                        genres = genres.take(genres.length - 2),
                        overview = data.overview,
                        posterPath = data.posterPath,
                        releaseDate = data.releaseDate,
                        runtime = data.runtime,
                        title = data.title,
                        voteAverage = data.voteAverage,
                        isFavorite = false
                )
                localDataSource.updateMovie(movie)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie)
        }
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                    remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShow>) {
                val movieList = ArrayList<TvShowEntity>()
                data.forEach {
                    val movie = TvShowEntity(
                            id = it.id,
                            backdropPath = it.backdropPath,
                            genres = null,
                            overview = it.overview,
                            posterPath = it.posterPath,
                            releaseDate = it.firstAirDate,
                            title = it.name,
                            voteAverage = it.voteAverage,
                            isFavorite = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertTvShows(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TVShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getDetailTvShow(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                    data != null && data.genres.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<TVShowDetailResponse>> =
                    remoteDataSource.getDetailTvShow(tvShowId.toString())

            override fun saveCallResult(data: TVShowDetailResponse) {
                var genres = ""
                data.genres.forEach {
                    genres += "${it.name}, "
                }

                val tvShow = TvShowEntity(
                        id = data.id,
                        backdropPath = data.backdropPath,
                        genres = genres.take(genres.length - 2),
                        overview = data.overview,
                        posterPath = data.posterPath,
                        releaseDate = data.firstAirDate,
                        title = data.name,
                        voteAverage = data.voteAverage,
                        isFavorite = false
                )
                localDataSource.updateTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow)
        }
    }

}