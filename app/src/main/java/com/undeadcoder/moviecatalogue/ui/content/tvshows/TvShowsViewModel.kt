package com.undeadcoder.moviecatalogue.ui.content.tvshows

import androidx.lifecycle.ViewModel
import com.undeadcoder.moviecatalogue.data.source.MovieRepository

class TvShowsViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getTvShows() = repository.getTVShows()

}