package com.undeadcoder.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.undeadcoder.moviecatalogue.data.source.local.LocalDataSource
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.data.source.remote.RemoteDataSource
import com.undeadcoder.moviecatalogue.utils.AppExecutors
import com.undeadcoder.moviecatalogue.utils.DataDummy
import com.undeadcoder.moviecatalogue.utils.LiveDataTestUtil
import com.undeadcoder.moviecatalogue.utils.PagedListUtil
import com.undeadcoder.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val repository = FakeMovieRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.getDummyRemoteMovies()
    private val movieId = moviesResponse[0].id
    private val detailMovie = DataDummy.getDummyRemoteDetailMovie()

    private val tvShowsResponse = DataDummy.getDummyRemoteTvShows()
    private val tvShowId = tvShowsResponse[0].id
    private val detailTvShow = DataDummy.getDummyRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        repository.getMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntity.data)
        assertEquals(moviesResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        repository.getTvShows()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(tvShowsResponse.size, tvShowEntity.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = MutableLiveData<MovieEntity>()
        dummyDetailMovie.value = DataDummy.generateDetailMovie()
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyDetailMovie)

        val movie = LiveDataTestUtil.getValue(repository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(movie)
        assertEquals(detailMovie.id, movie.data?.id)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailTvShow = MutableLiveData<TvShowEntity>()
        dummyDetailTvShow.value = DataDummy.generateDetailTvShow()
        `when`(local.getDetailTvShow(tvShowId)).thenReturn(dummyDetailTvShow)

        val tvShow = LiveDataTestUtil.getValue(repository.getDetailTvShow(tvShowId))
        verify(local).getDetailTvShow(tvShowId)
        assertNotNull(tvShow)
        assertEquals(detailTvShow.id, tvShow.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntity)
        assertEquals(moviesResponse.size, movieEntity.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        repository.setFavoriteMovie(DataDummy.generateDetailMovie())
        verify(local).setFavoriteMovie(DataDummy.generateDetailMovie())
        verifyNoMoreInteractions(local)
    }

}