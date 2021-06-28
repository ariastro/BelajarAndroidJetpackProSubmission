package com.undeadcoder.moviecatalogue.ui.content.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.undeadcoder.moviecatalogue.data.source.MovieRepository
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(repository.getFavoriteMovies()).thenReturn(movies)
        val movie = viewModel.getFavoriteMovies().value
        verify(repository).getFavoriteMovies()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getFavoriteMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }

}