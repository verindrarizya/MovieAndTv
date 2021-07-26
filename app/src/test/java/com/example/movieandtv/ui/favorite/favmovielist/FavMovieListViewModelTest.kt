package com.example.movieandtv.ui.favorite.favmovielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavMovieListViewModelTest {

    private lateinit var viewModel: FavMovieListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var favMoviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = FavMovieListViewModel(dataRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyFavMovies = favMoviePagedList
        `when`(dummyFavMovies.size).thenReturn(5)

        val favMovies = MutableLiveData<PagedList<MovieEntity>>()
        favMovies.value = dummyFavMovies

        `when`(dataRepository.getFavoriteMovies()).thenReturn(favMovies)
        val favMovieEntities = viewModel.getFavMovies().value
        verify(dataRepository).getFavoriteMovies()
        assertNotNull(favMovieEntities)
        assertEquals(5, favMovieEntities?.size)

        viewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyFavMovies)
    }
}