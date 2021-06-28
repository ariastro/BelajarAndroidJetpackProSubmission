package com.undeadcoder.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.undeadcoder.moviecatalogue.BuildConfig
import com.undeadcoder.moviecatalogue.data.source.remote.response.*
import com.undeadcoder.moviecatalogue.data.source.remote.api.ApiService
import com.undeadcoder.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    fun getMovies(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Movie>>>()
        val client = apiService.getMovies(BuildConfig.API_KEY)
        client.enqueue(object : Callback<GetMoviesResponse> {
            override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.movies as List<Movie>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                Log.e("getMovies", "onFailure: ${t.localizedMessage}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getDetailMovie(movieId: String): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        val client = apiService.getMovieDetail(movieId, BuildConfig.API_KEY)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                resultDetailMovie.value = ApiResponse.success(response.body() as MovieDetailResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("getDetailMovie", "onFailure: ${t.localizedMessage}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailMovie
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShow>>>()
        val client = apiService.getTVShows(BuildConfig.API_KEY)
        client.enqueue(object : Callback<GetTVShowsResponse> {
            override fun onResponse(call: Call<GetTVShowsResponse>, response: Response<GetTVShowsResponse>) {
                resultTvShows.value = ApiResponse.success(response.body()?.tvShows as List<TvShow>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<GetTVShowsResponse>, t: Throwable) {
                Log.e("getTvShows", "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShows
    }

    fun getDetailTvShow(tvShowId: String): LiveData<ApiResponse<TVShowDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<TVShowDetailResponse>>()
        val client = apiService.getTVShowDetail(tvShowId, BuildConfig.API_KEY)
        client.enqueue(object : Callback<TVShowDetailResponse> {
            override fun onResponse(call: Call<TVShowDetailResponse>, response: Response<TVShowDetailResponse>) {
                resultDetailTvShow.value = ApiResponse.success(response.body() as TVShowDetailResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVShowDetailResponse>, t: Throwable) {
                Log.e("getDetailTvShow", "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailTvShow
    }

}