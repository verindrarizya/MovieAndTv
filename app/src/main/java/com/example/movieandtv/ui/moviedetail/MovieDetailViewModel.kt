package com.example.movieandtv.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.data.source.local.entity.MovieEntity

class MovieDetailViewModel(private val mDataRepository: DataRepository): ViewModel() {

    private var movieId: Int = 0

    fun setMovieId(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovieDetail(): LiveData<MovieEntity> = mDataRepository.getMovie(movieId)

    fun setMovieFavorite(movieEntity: MovieEntity) {
        mDataRepository.setFavoriteMovie(movieEntity, !movieEntity.isFavorite)
    }
}