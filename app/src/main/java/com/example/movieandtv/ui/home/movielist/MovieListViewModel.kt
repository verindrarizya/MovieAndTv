package com.example.movieandtv.ui.home.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.vo.Resource

class MovieListViewModel(private val mDataRepository: DataRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = mDataRepository.getMovies()

}