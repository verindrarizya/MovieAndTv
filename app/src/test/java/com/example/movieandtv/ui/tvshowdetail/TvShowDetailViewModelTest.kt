package com.example.movieandtv.ui.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.TvShowEntity
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
class TvShowDetailViewModelTest {

    private lateinit var viewModel: TvShowDetailViewModel

    private val dummyTvShow = DataDummy.generateDummyTvShowsData()[0]
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(dataRepository)
        viewModel.setTvShowId(tvShowId)
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        `when`(dataRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShowDetail().value

        verify(dataRepository).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity?.id)
        assertEquals(dummyTvShow.title, tvShowEntity?.title)
        assertEquals(dummyTvShow.poster, tvShowEntity?.poster)
        assertEquals(dummyTvShow.date, tvShowEntity?.date)
        assertEquals(dummyTvShow.rate, tvShowEntity?.rate)
        assertEquals(dummyTvShow.overview, tvShowEntity?.overview)

        viewModel.getTvShowDetail().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        `when`(dataRepository.setFavoriteTvShow(dummyTvShow, true)).then {
            dummyTvShow.isFavorite = true
            null
        }
        viewModel.setTvShowFavorite(dummyTvShow)

        verify(dataRepository).setFavoriteTvShow(dummyTvShow, true)
        assertEquals(true, dummyTvShow.isFavorite)
    }

    @Test
    fun setUnfavoriteTvShow() {
        val dumTvShow = dummyTvShow
        dumTvShow.isFavorite = true

        `when`(dataRepository.setFavoriteTvShow(dumTvShow, false)).then {
            dumTvShow.isFavorite = false
            null
        }
        viewModel.setTvShowFavorite(dumTvShow)

        verify(dataRepository).setFavoriteTvShow(dumTvShow, false)
        assertEquals(false, dumTvShow.isFavorite)
    }
}