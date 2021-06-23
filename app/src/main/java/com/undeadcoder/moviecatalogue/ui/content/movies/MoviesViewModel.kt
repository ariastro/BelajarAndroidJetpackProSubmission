package com.undeadcoder.moviecatalogue.ui.content.movies

import androidx.lifecycle.ViewModel
import com.undeadcoder.moviecatalogue.data.source.MovieRepository

class MoviesViewModel(private val repository: MovieRepository): ViewModel() {

    fun getMovies() = repository.getMovies()

}