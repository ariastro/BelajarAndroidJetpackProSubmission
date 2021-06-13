package com.undeadcoder.moviecatalogue1.ui.detail

import com.undeadcoder.moviecatalogue1.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setMovieId(movieId)
        viewModel.setTvShowId(tvShowId)
    }

    @Test
    fun getMovie() {
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie.id)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        assertEquals(dummyMovie.genre, movie.genre)
        assertEquals(dummyMovie.score, movie.score)
        assertEquals(dummyMovie.overview, movie.overview)
        assertEquals(dummyMovie.poster, movie.poster)
        assertEquals(dummyMovie.banner, movie.banner)
    }

    @Test
    fun getTVShow() {
        val tvShow = viewModel.getTVShow()
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow.id)
        assertEquals(dummyTvShow.title, tvShow.title)
        assertEquals(dummyTvShow.releaseDate, tvShow.releaseDate)
        assertEquals(dummyTvShow.genre, tvShow.genre)
        assertEquals(dummyTvShow.score, tvShow.score)
        assertEquals(dummyTvShow.overview, tvShow.overview)
        assertEquals(dummyTvShow.poster, tvShow.poster)
        assertEquals(dummyTvShow.banner, tvShow.banner)
    }
}