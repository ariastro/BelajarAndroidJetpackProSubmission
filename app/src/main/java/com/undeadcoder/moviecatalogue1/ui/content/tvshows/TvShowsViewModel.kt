package com.undeadcoder.moviecatalogue1.ui.content.tvshows

import androidx.lifecycle.ViewModel
import com.undeadcoder.moviecatalogue1.utils.DataDummy

class TvShowsViewModel : ViewModel() {

    fun getTvShows() = DataDummy.generateDummyTvShows()

}