package com.example.movieandtv.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.data.source.local.room.dao.IMovieDao
import com.example.movieandtv.data.source.local.room.dao.ITvShowDao

class LocalDataSource private constructor(
    private val mMovieDao: IMovieDao,
    private val mTvShowDao: ITvShowDao) {

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: IMovieDao, tvShowDao: ITvShowDao): LocalDataSource {
            return instance ?: LocalDataSource(movieDao, tvShowDao)
        }
    }

    // Movies
    fun getMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovies()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getFavoriteMovies()

    fun getMovie(movieId: Int): LiveData<MovieEntity> = mMovieDao.getMovie(movieId)

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun makeMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieDao.updateMovie(movie)
    }

    // TvShow
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = mTvShowDao.getTvShows()

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = mTvShowDao.getFavoriteTvShows()

    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity> = mTvShowDao.getTvShow(tvShowId)

    fun insertTvShows(tvShows: List<TvShowEntity>) = mTvShowDao.insertTvShows(tvShows)

    fun makeTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mTvShowDao.updateTvShow(tvShow)
    }
}