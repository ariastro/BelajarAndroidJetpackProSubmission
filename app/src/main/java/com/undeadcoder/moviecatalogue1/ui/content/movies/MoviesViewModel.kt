package com.undeadcoder.moviecatalogue1.ui.content.movies

import androidx.lifecycle.ViewModel
import com.undeadcoder.moviecatalogue1.utils.DataDummy

class MoviesViewModel : ViewModel() {

    fun getMovies() = DataDummy.generateDummyMovies()

}