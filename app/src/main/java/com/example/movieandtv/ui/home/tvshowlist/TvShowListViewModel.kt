package com.example.movieandtv.ui.home.tvshowlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.vo.Resource

class TvShowListViewModel(private val mDataRepository: DataRepository): ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = mDataRepository.getTvShows()

}