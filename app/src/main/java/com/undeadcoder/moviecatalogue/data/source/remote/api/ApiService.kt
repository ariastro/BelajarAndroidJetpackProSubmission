package com.undeadcoder.moviecatalogue.data.source.remote.api

import com.undeadcoder.moviecatalogue.data.source.remote.response.GetMoviesResponse
import com.undeadcoder.moviecatalogue.data.source.remote.response.GetTVShowsResponse
import com.undeadcoder.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.undeadcoder.moviecatalogue.data.source.remote.response.TVShowDetailResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String) : Call<GetMoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: String,
                       @Query("api_key") apiKey: String) : Call<MovieDetailResponse>

    @GET("tv/popular")
    fun getTVShows(@Query("api_key") apiKey: String) : Call<GetTVShowsResponse>

    @GET("tv/{id}")
    fun getTVShowDetail(@Path("id") id: String,
                        @Query("api_key") apiKey: String) : Call<TVShowDetailResponse>

}