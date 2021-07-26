package com.example.movieandtv.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieandtv.data.DataRepository
import com.example.movieandtv.di.Injection
import com.example.movieandtv.ui.favorite.favmovielist.FavMovieListViewModel
import com.example.movieandtv.ui.favorite.favtvshowlist.FavTvShowListViewModel
import com.example.movieandtv.ui.home.movielist.MovieListViewModel
import com.example.movieandtv.ui.home.tvshowlist.TvShowListViewModel
import com.example.movieandtv.ui.moviedetail.MovieDetailViewModel
import com.example.movieandtv.ui.tvshowdetail.TvShowDetailViewModel

class DataViewModelFactory private constructor(private val mDataRepository: DataRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: DataViewModelFactory? = null

        fun getInstance(context: Context): DataViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: DataViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieListViewModel::class.java) -> MovieListViewModel(mDataRepository) as T
            modelClass.isAssignableFrom(TvShowListViewModel::class.java) -> TvShowListViewModel(mDataRepository) as T
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> MovieDetailViewModel(mDataRepository) as T
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> TvShowDetailViewModel(mDataRepository) as T
            modelClass.isAssignableFrom(FavMovieListViewModel::class.java) -> FavMovieListViewModel(mDataRepository) as T
            modelClass.isAssignableFrom(FavTvShowListViewModel::class.java) -> FavTvShowListViewModel(mDataRepository) as T

            // if none of the above match, then Throw
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}