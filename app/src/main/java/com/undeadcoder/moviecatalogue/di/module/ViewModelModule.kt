package com.undeadcoder.moviecatalogue.di.module

import com.undeadcoder.moviecatalogue.ui.content.movies.MoviesViewModel
import com.undeadcoder.moviecatalogue.ui.content.tvshows.TvShowsViewModel
import com.undeadcoder.moviecatalogue.ui.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}