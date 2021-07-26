package com.example.movieandtv.ui.favorite.favtvshowlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.TvShowEntity

class FavTvShowListViewModel(private val mDataRepository: DataRepository): ViewModel() {

    fun getFavTvShows(): LiveData<PagedList<TvShowEntity>> = mDataRepository.getFavoriteTvShows()

}