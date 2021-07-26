package com.example.movieandtv.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.vo.Resource

interface IDataSource {

    // Movies
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getMovie(movieId: Int): LiveData<MovieEntity>

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)

    // Tv Shows
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean)

}