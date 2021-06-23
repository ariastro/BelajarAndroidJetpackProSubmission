package com.undeadcoder.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.undeadcoder.moviecatalogue.data.DetailEntity
import com.undeadcoder.moviecatalogue.data.source.MovieRepository

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getDetailContent(isMovie: Boolean, id: String): LiveData<DetailEntity> {
        return if (isMovie) {
            repository.getDetailMovie(id)
        } else {
            repository.getDetailTvShow(id)
        }
    }

}