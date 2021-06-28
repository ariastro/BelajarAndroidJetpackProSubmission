package com.undeadcoder.moviecatalogue.ui.content.favorites

import androidx.lifecycle.ViewModel
import com.undeadcoder.moviecatalogue.data.source.MovieRepository

class FavoriteViewModel(private val repository: MovieRepository): ViewModel() {

    fun getFavoriteMovies() = repository.getFavoriteMovies()

    fun getFavoriteTvShows() = repository.getFavoriteTvShows()

}