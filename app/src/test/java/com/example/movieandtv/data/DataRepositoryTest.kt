package com.example.movieandtv.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movieandtv.data.source.local.LocalDataSource
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.data.source.remote.RemoteDataSource
import com.example.movieandtv.utils.DataDummy
import com.example.movieandtv.utils.LiveDataTestUtil
import com.example.movieandtv.utils.PagedListUtil
import com.example.movieandtv.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val dataRepository = FakeDataRepository(remote, local)

    private val movies = DataDummy.generateDummyMoviesData()
    private val movie = movies[0]
    private val movieId = movie.id

    private val tvShows = DataDummy.generateDummyTvShowsData()
    private val tvShow = tvShows[0]
    private val tvShowId = tvShow.id

    private val deltaValue = 0.01

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        dataRepository.getMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movies))
        verify(local).getMovies()

        assertNotNull(movieEntities.data)
        assertEquals(movies.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows()).thenReturn(dataSourceFactory)
        dataRepository.getTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(tvShows))
        verify(local).getTvShows()

        assertNotNull(tvShowEntities.data)
        assertEquals(tvShows.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovieEntity = MutableLiveData<MovieEntity>()
        dummyMovieEntity.value = movie
        `when`(local.getMovie(movieId)).thenReturn(dummyMovieEntity)

        val movieEntity = LiveDataTestUtil.getValue(dataRepository.getMovie(movieId))
        verify(local).getMovie(movieId)

        assertNotNull(movieEntity)
        assertEquals(movie.id, movieEntity.id)
        assertEquals(movie.title, movieEntity.title)
        assertEquals(movie.poster, movieEntity.poster)
        assertEquals(movie.date, movieEntity.date)
        assertEquals(movie.rate, movieEntity.rate, deltaValue)
        assertEquals(movie.overview, movieEntity.overview)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShowEntity = MutableLiveData<TvShowEntity>()
        dummyTvShowEntity.value = tvShow
        `when`(local.getTvShow(tvShowId)).thenReturn(dummyTvShowEntity)

        val tvShowEntity = LiveDataTestUtil.getValue(dataRepository.getTvShow(tvShowId))
        verify(local).getTvShow(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(tvShow.id, tvShowEntity.id)
        assertEquals(tvShow.title, tvShowEntity.title)
        assertEquals(tvShow.poster, tvShowEntity.poster)
        assertEquals(tvShow.date, tvShowEntity.date)
        assertEquals(tvShow.rate, tvShowEntity.rate, deltaValue)
        assertEquals(tvShow.overview, tvShowEntity.overview)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        dataRepository.getFavoriteMovies()

        val favMovieEntities = Resource.success(PagedListUtil.mockPagedList(movies))
        verify(local).getFavoriteMovies()

        assertNotNull(favMovieEntities)
        assertEquals(movies.size.toLong(), favMovieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        dataRepository.getFavoriteTvShows()

        val favTvShowEntities = Resource.success(PagedListUtil.mockPagedList(tvShows))
        verify(local).getFavoriteTvShows()

        assertNotNull(favTvShowEntities)
        assertEquals(tvShows.size.toLong(), favTvShowEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteMovie() {
        `when`(local.makeMovieFavorite(movie, true)).then {
            movie.isFavorite = true
            null
        }

        dataRepository.setFavoriteMovie(movie, true)

        verify(local).makeMovieFavorite(movie, true)
        assertEquals(true, movie.isFavorite)
    }

    @Test
    fun unsetFavoriteMovie() {
        val dumMovie = movie
        dumMovie.isFavorite = true

        `when`(local.makeMovieFavorite(dumMovie, false)).then {
            dumMovie.isFavorite = false
            null
        }

        dataRepository.setFavoriteMovie(dumMovie, false)

        verify(local).makeMovieFavorite(dumMovie, false)
        assertEquals(false, dumMovie.isFavorite)
    }

    @Test
    fun setFavoriteTvShow() {

        `when`(local.makeTvShowFavorite(tvShow, true)).then {
            tvShow.isFavorite = true
            null
        }

        dataRepository.setFavoriteTvShow(tvShow, true)

        verify(local).makeTvShowFavorite(tvShow, true)
        assertEquals(true, tvShow.isFavorite)
    }

    @Test
    fun unsetFavoriteTvShow() {
        val dumTvShow = tvShow
        dumTvShow.isFavorite = true

        `when`(local.makeTvShowFavorite(dumTvShow, false)).then {
            dumTvShow.isFavorite = false
            null
        }

        dataRepository.setFavoriteTvShow(dumTvShow, false)

        verify(local).makeTvShowFavorite(dumTvShow, false)
        assertEquals(false, dumTvShow.isFavorite)
    }
}