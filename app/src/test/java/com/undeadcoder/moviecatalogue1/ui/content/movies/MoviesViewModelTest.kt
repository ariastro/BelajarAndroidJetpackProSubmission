package com.undeadcoder.moviecatalogue1.ui.content.movies

import org.junit.Assert
import org.junit.Test
import org.junit.Before

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        Assert.assertNotNull(movies)
        Assert.assertEquals(10, movies.size)
    }
}