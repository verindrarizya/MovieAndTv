package com.example.movieandtv.ui.favorite.favmovielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.MovieEntity

class FavMovieListViewModel(private val mDataRepository: DataRepository): ViewModel() {

    fun getFavMovies(): LiveData<PagedList<MovieEntity>> = mDataRepository.getFavoriteMovies()

}