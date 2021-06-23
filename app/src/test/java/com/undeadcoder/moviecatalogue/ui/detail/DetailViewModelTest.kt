package com.undeadcoder.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.undeadcoder.moviecatalogue.data.DetailEntity
import com.undeadcoder.moviecatalogue.data.source.MovieRepository
import com.undeadcoder.moviecatalogue.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDetailMovie()
    private val dummyMovieId = dummyMovie.id.toString()

    private val dummyTvShow = DataDummy.generateDetailTvShow()
    private val dummyTvShowId = dummyTvShow.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<DetailEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<DetailEntity>()
        movie.value = dummyMovie

        `when`(repository.getDetailMovie(dummyMovieId)).thenReturn(movie)
        val detailEntity = viewModel.getDetailContent(true, dummyMovieId).value as DetailEntity
        verify(repository).getDetailMovie(dummyMovieId)
        assertNotNull(detailEntity)
        assertEquals(dummyMovie.backdropPath, detailEntity.backdropPath)
        assertEquals(dummyMovie.genres, detailEntity.genres)
        assertEquals(dummyMovie.id, detailEntity.id)
        assertEquals(dummyMovie.overview, detailEntity.overview)
        assertEquals(dummyMovie.posterPath, detailEntity.posterPath)
        assertEquals(dummyMovie.releaseDate, detailEntity.releaseDate)
        assertEquals(dummyMovie.runtime, detailEntity.runtime)
        assertEquals(dummyMovie.title, detailEntity.title)
        assertEquals(dummyMovie.voteAverage.toString(), detailEntity.voteAverage.toString())

        viewModel.getDetailContent(true, dummyMovieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<DetailEntity>()
        tvShow.value = dummyTvShow

        `when`(repository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)
        val detailEntity = viewModel.getDetailContent(false, dummyTvShowId).value as DetailEntity
        verify(repository).getDetailTvShow(dummyTvShowId)
        assertNotNull(detailEntity)
        assertEquals(dummyTvShow.backdropPath, detailEntity.backdropPath)
        assertEquals(dummyTvShow.genres, detailEntity.genres)
        assertEquals(dummyTvShow.id, detailEntity.id)
        assertEquals(dummyTvShow.overview, detailEntity.overview)
        assertEquals(dummyTvShow.posterPath, detailEntity.posterPath)
        assertEquals(dummyTvShow.releaseDate, detailEntity.releaseDate)
        assertEquals(dummyTvShow.runtime, detailEntity.runtime)
        assertEquals(dummyTvShow.title, detailEntity.title)
        assertEquals(dummyTvShow.voteAverage.toString(), detailEntity.voteAverage.toString())

        viewModel.getDetailContent(false, dummyTvShowId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyTvShow)
    }
}