package com.undeadcoder.moviecatalogue1.ui.content.tvshows

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShows = viewModel.getTvShows()
        Assert.assertNotNull(tvShows)
        Assert.assertEquals(10, tvShows.size)
    }
}