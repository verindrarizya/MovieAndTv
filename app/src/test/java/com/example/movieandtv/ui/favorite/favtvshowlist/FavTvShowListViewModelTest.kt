package com.example.movieandtv.ui.favorite.favtvshowlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.TvShowEntity
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
class FavTvShowListViewModelTest {

    private lateinit var viewModel: FavTvShowListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var favTvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavTvShowListViewModel(dataRepository)
    }

    @Test
    fun getFavTvShows() {
        val dummyFavTvShows = favTvShowPagedList
        `when`(dummyFavTvShows.size).thenReturn(5)

        val favTvShows = MutableLiveData<PagedList<TvShowEntity>>()
        favTvShows.value = dummyFavTvShows

        `when`(dataRepository.getFavoriteTvShows()).thenReturn(favTvShows)
        val favTvShowEntities = viewModel.getFavTvShows().value
        verify(dataRepository).getFavoriteTvShows()
        assertNotNull(favTvShowEntities)
        assertEquals(5, favTvShowEntities?.size)

        viewModel.getFavTvShows().observeForever(observer)
        verify(observer).onChanged(dummyFavTvShows)
    }
}