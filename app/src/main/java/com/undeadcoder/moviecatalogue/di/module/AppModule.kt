package com.undeadcoder.moviecatalogue.di.module

import android.content.Context
import com.undeadcoder.moviecatalogue.BuildConfig
import com.undeadcoder.moviecatalogue.data.source.local.LocalDataSource
import com.undeadcoder.moviecatalogue.data.source.local.room.MovieDao
import com.undeadcoder.moviecatalogue.data.source.local.room.MovieDatabase
import com.undeadcoder.moviecatalogue.data.source.remote.RemoteDataSource
import com.undeadcoder.moviecatalogue.data.source.remote.api.ApiService
import com.undeadcoder.moviecatalogue.utils.AppExecutors
import com.undeadcoder.moviecatalogue.utils.Constants.TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { provideMovieDao(androidApplication()) }
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single { AppExecutors() }
}

private fun provideMovieDao(context: Context): MovieDao {
    val database = MovieDatabase.getInstance(context)
    return database.movieDao()
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)