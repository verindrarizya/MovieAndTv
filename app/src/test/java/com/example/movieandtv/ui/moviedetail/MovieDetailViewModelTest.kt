package com.example.movieandtv.ui.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel

    private val dummyMovie = DataDummy.generateDummyMoviesData()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(dataRepository)
        viewModel.setMovieId(movieId)
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(dataRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovieDetail().value

        verify(dataRepository).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.poster, movieEntity?.poster)
        assertEquals(dummyMovie.date, movieEntity?.date)
        assertEquals(dummyMovie.rate, movieEntity?.rate)
        assertEquals(dummyMovie.overview, movieEntity?.overview)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun setFavoriteMovie() {
        `when`(dataRepository.setFavoriteMovie(dummyMovie, true)).then {
            dummyMovie.isFavorite = true
            null
        }
        viewModel.setMovieFavorite(dummyMovie)

        verify(dataRepository).setFavoriteMovie(dummyMovie, true)
        assertEquals(true, dummyMovie.isFavorite)
    }

    @Test
    fun setUnfavoriteMovie() {
        val dumMovie = dummyMovie
        dumMovie.isFavorite = true

        `when`(dataRepository.setFavoriteMovie(dumMovie, false)).then {
            dummyMovie.isFavorite = false
            0
        }
        viewModel.setMovieFavorite(dumMovie)
        verify(dataRepository).setFavoriteMovie(dumMovie, false)
        assertEquals(false, dumMovie.isFavorite)
    }
}