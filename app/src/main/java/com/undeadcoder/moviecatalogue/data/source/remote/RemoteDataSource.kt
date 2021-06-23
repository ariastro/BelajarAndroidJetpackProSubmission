package com.undeadcoder.moviecatalogue.data.source.remote

import android.util.Log
import com.undeadcoder.moviecatalogue.BuildConfig
import com.undeadcoder.moviecatalogue.data.source.remote.response.*
import com.undeadcoder.moviecatalogue.data.source.remote.api.ApiService
import com.undeadcoder.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val client = apiService.getMovies(BuildConfig.API_KEY)
        client.enqueue(object : Callback<GetMoviesResponse> {
            override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                callback.onMoviesLoaded(response.body()?.movies)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                Log.e("getMovies", "onFailure: ${t.localizedMessage}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailMovie(callback: LoadDetailMovieCallback, movieId: String) {
        EspressoIdlingResource.increment()
        val client = apiService.getMovieDetail(movieId, BuildConfig.API_KEY)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                callback.onDetailMovieLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("getDetailMovie", "onFailure: ${t.localizedMessage}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        val client = apiService.getTVShows(BuildConfig.API_KEY)
        client.enqueue(object : Callback<GetTVShowsResponse> {
            override fun onResponse(call: Call<GetTVShowsResponse>, response: Response<GetTVShowsResponse>) {
                callback.onTvShowsLoaded(response.body()?.tvShows)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<GetTVShowsResponse>, t: Throwable) {
                Log.e("getTvShows", "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailTvShow(callback: LoadDetailTvShowCallback, tvShowId: String) {
        EspressoIdlingResource.increment()
        val client = apiService.getTVShowDetail(tvShowId, BuildConfig.API_KEY)
        client.enqueue(object : Callback<TVShowDetailResponse> {
            override fun onResponse(call: Call<TVShowDetailResponse>, response: Response<TVShowDetailResponse>) {
                callback.onDetailTvShowLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVShowDetailResponse>, t: Throwable) {
                Log.e("getDetailTvShow", "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies : List<Movie>?)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieLoaded(movieDetail : MovieDetailResponse?)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsLoaded(tvShows : List<TvShow>?)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowLoaded(tvShowDetail: TVShowDetailResponse?)
    }
}