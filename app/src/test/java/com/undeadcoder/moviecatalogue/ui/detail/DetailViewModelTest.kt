package com.undeadcoder.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.undeadcoder.moviecatalogue.data.source.MovieRepository
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.utils.DataDummy
import com.undeadcoder.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = Resource.success(DataDummy.generateDetailMovie())
    private val dummyMovieId = dummyMovie.data?.id ?: 0

    private val dummyTvShow = Resource.success(DataDummy.generateDetailTvShow())
    private val dummyTvShowId = dummyTvShow.data?.id ?: 0

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(repository.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.getDetailContent(true, dummyMovieId.toString())
        viewModel.detailMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)

    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(repository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.getDetailContent(false, dummyTvShowId.toString())
        viewModel.detailTvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}